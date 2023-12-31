import json
import logging
import sys

from Advisor import Advisor
from Assistant import Assistant
from Course import Course
from Department import Department
from Grade import Grade
from LaboratorySection import LaboratorySection
from Lecturer import Lecturer
from Registration import Registration
from Student import Student
from Transcript import Transcript


class JSONReader:

    def __init__(self):
        self.coursePrerequisiteCourseCodesMap = {}
        self.lecturerIDCoursesMap = {}
        self.assistantIDAssistantMap = {}
        self.advisorIDCoursesMap = {}
        self.studentIDAdvisorIDMap = {}
        self.studentRegistrationMap = {}
        self.sectionCodeSectionMap = {}
        self.mapper = None
        self.jsonNode = None
        self.university = None
        self.department = None

    def readDepartments(self, university):
        with open("jsons/departments.json", 'r', encoding='utf-8') as f:
            data = f.read()

        self.university = university
        # Parse the JSON file into a Python object.
        self.jsonNode = json.loads(data)

        for department in self.jsonNode:
            departmentName = department.get("departmentName")

            # Create a new department object with the retrieved details.
            newDepartment = Department(departmentName)
            logging.info(f"Department {departmentName} created.")
            university.getDepartments().append(newDepartment)

    def start(self, department):
        self.department = department
        logging.info(f"Reading JSON data and synchronizing objects for department: {department.departmentName}")
        self.readJson()
        self.syncObjects()
        logging.info(f"JSON data read and objects synchronized for department: {department.departmentName}")

    def readJson(self):
        self.readCourses()
        self.readAssistants()
        self.readLabSections()
        self.readLecturers()
        self.readStudents()
        self.readRequests()
        self.readAdvisors()

    def readCourses(self):
        try:
            with open("jsons/courses.json", 'r', encoding='utf-8') as f:
                coursesArray = json.load(f)

            for course in coursesArray:
                # Retrieve the course name, course code, etc. from the JSON.
                courseName = course.get("courseName")
                courseCode = course.get("courseCode")
                courseType = course.get("courseType")
                courseCredit = course.get("courseCredit")
                semester = course.get("semester")
                capacity = course.get("capacity")
                hour = course.get("hour")
                day = course.get("day")

                newCourse = Course(courseName, courseCode, courseType, courseCredit,
                                   semester, capacity, hour, day)

                logging.info(f"Course {courseCode} created.")
                self.department.courses.append(newCourse)
                self.department.courseCodeCourseMap.update({courseCode: newCourse})

                # Get the array of prerequisite course codes from the course.
                preRequisiteCourseCodes = course.get("preRequisiteCourseCodes")
                self.coursePrerequisiteCourseCodesMap[newCourse] = preRequisiteCourseCodes


        except Exception as e:
            print("There is a problem with the courses.json file.")
            logging.error("There is a problem with the courses.json file.")
            exit(0)

    def readAssistants(self):
        try:
            with open("jsons/assistants.json", 'r', encoding='utf-8') as f:
                data = f.read()

            self.jsonNode = json.loads(data)

            for lecturer in self.jsonNode:
                id = lecturer.get("assistantID")
                name = lecturer.get("name")
                surname = lecturer.get("surname")

                # Create a new Lecturer object with the retrieved details.
                newAssistant = Assistant(id, name, surname)
                logging.info(f"Assistant created with ID: {id}")

                self.department.assistants.append(newAssistant)
                self.assistantIDAssistantMap[id] = newAssistant
                newAssistant.department = self.department
        except Exception:
            print("There is a problem with the assistants.json file.")
            logging.error("There is a problem with the assistants.json file.")
            sys.exit(0)

    def readLabSections(self):
        try:
            with open("jsons/labSections.json", 'r', encoding='utf-8') as f:
                labSectionsArray = json.load(f)

            for labSection in labSectionsArray:
                # Parse the labSection fields.
                labSectionCode = labSection.get("labSectionCode")
                courseCode = labSection.get("courseCode")
                day = labSection.get("day")
                hour = labSection.get("hour")
                capacity = labSection.get("capacity")
                assistantID = labSection.get("assistantID")

                course = self.department.courseCodeCourseMap.get(courseCode)

                newLabSection = LaboratorySection(labSectionCode, capacity, hour, day)

                logging.info(f"Lab section created with code: {labSectionCode}")

                newLabSection.assistant = self.assistantIDAssistantMap.get(assistantID)
                self.sectionCodeSectionMap[labSectionCode] = newLabSection

                self.department.laboratorySections.append(newLabSection)
                self.department.sectionCodeCourseMap[labSectionCode] = course
                self.department.labSectionCourseMap[newLabSection] = course

        except Exception as e:
            print("There is a problem with the labSections.json file.")
            logging.error("There is a problem with the labSections.json file.")
            exit(0)

    def readLecturers(self):
        try:
            with open("jsons/lecturers.json", 'r', encoding='utf-8') as f:
                lecturersArray = json.load(f)

            for lecturer in lecturersArray:
                id = lecturer.get("lecturerID")
                name = lecturer.get("name")
                surname = lecturer.get("surname")

                newLecturer = Lecturer(id, name, surname, None, None)
                logging.info(f"Lecturer created with ID: {id}")

                self.department.lecturers.append(newLecturer)
                newLecturer.department = self.department

                # Get the array of lessons taught from the lecturer.
                lessonsTaughtArray = lecturer.get("lessonsTaught")
                lessonsTaught = [lesson for lesson in lessonsTaughtArray]

                self.lecturerIDCoursesMap[id] = lessonsTaught

        except Exception as e:
            print("There is a problem with the lecturers.json file.")
            logging.error("There is a problem with the lecturers.json file.")
            exit(0)

    def readStudents(self):
        try:
            with open('jsons/students.json', 'r', encoding='utf-8') as f:
                studentsArray = json.load(f)

            for student in studentsArray:
                id = student.get("studentID")
                name = student.get("name")
                surname = student.get("surname")
                userName = student.get("userName")
                password = student.get("password")
                semester = student.get("semester")

                newStudent = Student(id, name, surname, userName, password, semester)
                logging.info(f"Student created with ID: {id}")
                newStudent.department = self.department

                labSectionsArray = student.get("labSections")
                for labSectionCode in labSectionsArray:
                    newStudent.labSections.append(self.sectionCodeSectionMap.get(labSectionCode))

                self.department.students.append(newStudent)
                self.university.userNamePersonMap[userName] = newStudent
                self.department.studentIDStudentMap[id] = newStudent

                advisorID = student.get("advisorID")
                self.studentIDAdvisorIDMap[id] = advisorID

                self.readTranscript(newStudent)  # assuming the existence of `readTranscript` method

        except Exception as e:
            print("There is a problem with the students.json file.")
            logging.error("There is a problem with the students.json file.")
            exit(0)

    def readTranscript(self, student):
        try:
            with open(f"jsons/Transcripts/{student.ID}.json", 'r', encoding='utf-8') as f:
                transcript = json.load(f)

            courseGradeMap = {}
            studentCourses = []
            courses = transcript.get("courses")

            for course in courses:
                letterGrade = course.get("letterGrade")
                courseCode = course.get("courseCode")
                course1 = self.department.courseCodeCourseMap.get(courseCode)

                if course1 not in studentCourses:
                    studentCourses.append(course1)
                    grades = []

                    if letterGrade == "null":
                        self.system.addToSchedule(course1, student)
                        course1.numberOfStudents += 1
                        grades.append(None)
                        courseGradeMap[course1] = grades
                    else:
                        grades.append(Grade(letterGrade))
                        courseGradeMap[course1] = grades
                else:
                    if letterGrade == "null":
                        self.system.addToSchedule(course1, student)
                        course1.numberOfStudents += 1
                        courseGradeMap[course1].append(None)
                    else:
                        courseGradeMap[course1].append(Grade(letterGrade))

            newTranscript = Transcript(student)
            self.department.transcripts.append(newTranscript)
            newTranscript.studentCourses = studentCourses

            student.transcript = newTranscript
            student.transcript.courseGradeMap = courseGradeMap
            newTranscript.calculateValues()

            logging.info(f"Transcript created for student with ID: {student.ID}")

        except Exception as e:
            print(f"There is a problem with the {student.ID}.json file.")
            logging.error(f"There is a problem with the {student.ID}.json file.")
            exit(0)

    def readRequests(self):
        try:
            with open("jsons/requests.json", 'r', encoding='utf-8') as f:
                requestsArray = json.load(f)

            for request in requestsArray:
                draftCourses = []
                studentID = request.get("studentID")
                courseCodesArray = request.get("courses")

                for courseCode in courseCodesArray:
                    course1 = self.department.courseCodeCourseMap.get(courseCode)
                    draftCourses.append(course1)

                student = self.department.studentIDStudentMap.get(studentID)
                student.draft = draftCourses
                student.hasRequest = True
                self.studentRegistrationMap[student] = Registration(student, draftCourses)

        except Exception as e:
            print("There is a problem with the requests.json file.")
            logging.error("There is a problem with the requests.json file.")
            exit(0)

    def readAdvisors(self):
        try:
            with open("jsons/advisors.json", 'r', encoding='utf-8') as f:
                advisorsArray = json.load(f)

            for advisor in advisorsArray:
                id = advisor.get("advisorID")
                name = advisor.get("name")
                surname = advisor.get("surname")
                userName = advisor.get("userName")
                password = advisor.get("password")

                newAdvisor = Advisor(id, name, surname, userName, password)
                logging.info(f"Advisor created with ID: {id}")

                self.department.advisors.append(newAdvisor)
                newAdvisor.setDepartment(self.department)

                self.department.advisorIDAdvisorMap[id] = newAdvisor
                self.university.userNamePersonMap[userName] = newAdvisor

                lessonsTaughtArray = advisor.get("lessonsTaught")
                lessonsTaught = [lesson for lesson in lessonsTaughtArray]

                self.advisorIDCoursesMap[id] = lessonsTaught

        except Exception as e:
            print("There is a problem with the advisors.json file.")
            logging.error("There is a problem with the advisors.json file.")
            exit(0)

    def syncObjects(self):
        # Sync for courses
        # For each course in the department, add its prerequisite courses and course sections
        for course in self.department.courses:
            for courseCode in self.coursePrerequisiteCourseCodesMap[course]:
                course.preRequisiteCourses.append(self.department.courseCodeCourseMap[courseCode])

        # Sync for lab sections
        for labSection in self.department.laboratorySections:
            course = self.department.labSectionCourseMap[labSection]
            course.laboratorySections.append(labSection)

        # Sync for lecturers
        # For each lecturer in the department, add the courses they teach and set them as the lecturer for each course
        for lecturer in self.department.lecturers:
            for courseCode in self.lecturerIDCoursesMap[lecturer.ID]:
                lecturer.lessonsTaught.append(self.department.courseCodeCourseMap[courseCode])
            for course in lecturer.lessonsTaught:
                course.lecturer = lecturer

        # Sync for advisors
        # For each student in the department, add them to their advisor's list of advised students and add their registration to the advisor's request list
        # For each advisor in the department, add the courses they teach
        for student in self.department.students:
            advisor = self.department.advisorIDAdvisorMap[self.studentIDAdvisorIDMap[student.ID]]
            student.advisor = advisor
            advisor.studentsAdvised.append(student)
            if student in self.studentRegistrationMap and self.studentRegistrationMap[student] is not None:
                advisor.requestList.append(self.studentRegistrationMap[student])
        for advisor in self.department.advisors:
            for courseCode in self.advisorIDCoursesMap[advisor.ID]:
                advisor.lessonsTaught.append(self.department.courseCodeCourseMap[courseCode])
