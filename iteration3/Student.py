import logging

import CourseRegistrationSystem
from ConsoleColours import ConsoleColours
from Course import Course
from IDisplayMenu import IDisplayMenu
from Person import Person
from Registration import Registration


class Student(Person, IDisplayMenu):
    def __init__(self, studentID, name, surname, userName, password, semester):
        super().__init__(studentID, name, surname, userName, password)
        self.draft = []
        self.availableCoursesToAdd = []
        self.availableCoursesToDrop = []
        self.labSections = []
        self.hasRequest = False
        self.transcript = None
        self.advisor = None
        self.notification = None
        self.semester = semester
        self.schedule = [[None for _ in range(8)] for _ in range(5)]

    def sendRequest(self):
        ConsoleColours.paintRedMenu()
        if self.hasRequest:
            print("You already have a request waiting for approval.")
            logging.warning(
                "Student " + self.ID + " already has a request waiting for " + self.advisor.ID + "'s approval.")
            ConsoleColours.resetColour()
        elif not self.draft:
            print("Your draft is empty!")
            ConsoleColours.resetColour()
        else:
            registration = Registration(self, self.draft)
            registration.addRequest(self.advisor)
            logging.info("Student " + self.ID + " sent a request to advisor " + self.advisor.ID + ".")

    def printMenu(self, menuType):
        match menuType:
            case "studentMenu":
                ConsoleColours.paintBlueMenu()
                print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                print(f"Welcome {self.name} {self.surname}!")
                print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

                if self.notification != None:
                    ConsoleColours.paintYellowMenu()
                    print(self.notification.message)
                    self.notification = None
                    ConsoleColours.paintBlueMenu()

                print("Please select from the following options:")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                print("                         Exit System -> 0")
                print("                 Course Registration -> 1")
                print("                     View Transcript -> 2")
                print("                             Log out -> 3")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                ConsoleColours.paintGreenMenu()
                print("Enter your choice: ")

            case "courseRegistrationMenu":
                ConsoleColours.paintBlueMenu()
                print("Course Registration Menu")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n")
                print("Please select from the following options:")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                print("                   Back to Main Menu -> 0")
                print("                 Course Status Check -> 1")
                print("                 Add Course to Draft -> 2")
                print("            Delete Course from Draft -> 3")
                print("                 Show request status -> 4")
                print("                        Send Request -> 5")
                print("                             Log out -> 6")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                ConsoleColours.paintGreenMenu()
                print("Enter your choice: ")

            case "courseSelectionMenu":
                ConsoleColours.paintBlueMenu()
                print("Course Selection Menu")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n")
                print("Please select from the following options:")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                print("    Back to Course Registration Menu -> 0")
                print("                       Add Mandatory -> 1")
                print("              Add Technical Elective -> 2")
                print("          Add Non-Technical Elective -> 3")
                print("      Add Faculty Technical Elective -> 4")
                print("                         Drop Course -> 5")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                ConsoleColours.paintGreenMenu()
                print("Enter your choice: ")
            case "addCourse":
                ConsoleColours.paintBlueMenu()
                print("Add Course Menu")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n")
                print("      Back -> 0")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                print("Here is the available courses to add:")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                ConsoleColours.paintPurpleMenu()

            case "dropCourse":
                ConsoleColours.paintBlueMenu()
                print("Drop Course Menu")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\n")
                print("       Back -> 0")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                print("Here is the available courses to drop:")
                print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
                ConsoleColours.paintPurpleMenu()

    def addCourseToDraft(self):
        if self.hasRequest:
            ConsoleColours.paintRedMenu()
            print("You can not add lecture because you have a request waiting for approval.")
            logging.warning(
                "Student " + self.ID + " can not add lecture because "
                                       "he/she has a request waiting for " + self.advisor.ID + "'s approval.")
            return
        ConsoleColours.paintBlueMenu()
        self.printMenu("courseSelectionMenu")
        switch_value = CourseRegistrationSystem.getInput()
        match switch_value:
            case 0:
                return
            case 1:
                self.addMandatoryCourse()
            case 2:
                self.addTechnicalElective()
            case 3:
                self.addNonTechnicalElective()
            case 4:
                self.addFacultyTechnicalElective()
            case 5:
                self.addCourseToDrop()
            case -1:
                ConsoleColours.paintRedMenu()
                print("Please enter valid number!")
            case _:
                ConsoleColours.paintRedMenu()
                print("Invalid input, please enter a valid number!")
        self.addCourseToDraft()

    def addMandatoryCourse(self):
        if self.maxCoursesReached():
            return
        self.computeAvailableMandatoryCourses()

        if not self.availableCoursesToAdd:
            ConsoleColours.paintRedMenu()
            print("You do not have any addable course!")
        else:
            self.printMenu("addCourse")
            for i, course in enumerate(self.availableCoursesToAdd):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00")
                print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.availableCoursesToAdd)} to add course: \n")
            userNumberInput1 = CourseRegistrationSystem().getInput()

            if 1 <= userNumberInput1 <= len(self.availableCoursesToAdd):
                self.chooseLabSection(self.availableCoursesToAdd[userNumberInput1 - 1])
                logging.info(
                    f"Student {self.ID} added {self.availableCoursesToAdd[userNumberInput1 - 1].courseCode} to draft.")
                self.draft.append(self.availableCoursesToAdd[userNumberInput1 - 1])
                del self.availableCoursesToAdd[userNumberInput1 - 1]
                if self.availableCoursesToAdd:
                    self.addMandatoryCourse()
            elif userNumberInput1 > len(self.availableCoursesToAdd) or userNumberInput1 < 0:
                ConsoleColours.paintRedMenu()
                logging.warning(f"Student {self.ID} entered invalid input.")
                print("Invalid input, please enter a valid number")
                self.addMandatoryCourse()
            else:
                return

    def chooseLabSection(self, course):
        if not course.laboratorySections:
            return
        availableLabSections = []
        ConsoleColours.paintBlueMenu()
        print("Here is the available laboratory sections to add:")
        ConsoleColours.paintPurpleMenu()
        for labSection in course.laboratorySections:
            labCourse = Course(None, labSection.laboratorySectionCode, None, 0, 0, 0, labSection.hour,
                               labSection.day)
            if self.hasCourseOverlap(labCourse, True) or labSection.capacity <= labSection.numberOfStudents:
                continue
            else:
                availableLabSections.append(labSection)

        for i, labSection in enumerate(availableLabSections):
            print(f"{i + 1}. {labSection.laboratorySectionCode}")
            print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

        print(f"Choose number between 1 to {len(availableLabSections)} to add laboratory section: \n")
        userNumberInput = CourseRegistrationSystem().getInput()

        if 1 <= userNumberInput <= len(availableLabSections):
            self.labSections.append(availableLabSections[userNumberInput - 1])
            availableLabSections[userNumberInput - 1].setNumberOfStudents(
                availableLabSections[userNumberInput - 1].numberOfStudents + 1)
        elif userNumberInput > len(availableLabSections) or userNumberInput <= 0:
            ConsoleColours.paintRedMenu()
            print("Invalid input, please enter a valid number")
            ConsoleColours.resetColour()
            self.chooseLabSection(course)
        else:
            return

    def addTechnicalElective(self):
        if self.maxCoursesReached():
            return
        self.computeAvailableTEAndFTECourses("TE")

        if not self.availableCoursesToAdd:
            ConsoleColours.paintRedMenu()
            print("You do not have any addable course!")
        else:
            self.printMenu("addCourse")
            for i, course in enumerate(self.availableCoursesToAdd):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00")
                print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.availableCoursesToAdd)} to add course: \n")
            userNumberInput1 = CourseRegistrationSystem().getInput()

            if 1 <= userNumberInput1 <= len(self.availableCoursesToAdd):
                self.draft.append(self.availableCoursesToAdd[userNumberInput1 - 1])
                logging.info(
                    f"Student {self.ID} added {self.availableCoursesToAdd[userNumberInput1 - 1].courseCode} to draft.")
                del self.availableCoursesToAdd[userNumberInput1 - 1]
                if self.availableCoursesToAdd:
                    self.addTechnicalElective()
            elif userNumberInput1 > len(self.availableCoursesToAdd) or userNumberInput1 < 0:
                ConsoleColours.paintRedMenu()
                print("Invalid input, please enter a valid number")
                self.addTechnicalElective()
            else:
                return

    def computeAvailableTEAndFTECourses(self, courseType):
        self.availableCoursesToAdd.clear()
        sum = 0
        mapGrade = self.transcript.courseGradeMap

        for course in self.transcript.studentCourses:
            if (course.semester() > 6
                    or course.courseType == "NTE"
                    or mapGrade[course][-1] is None
                    or mapGrade[course][-1].letterGrade > "DD"):
                continue
            sum += course.courseCredit

        if sum >= 155:
            for course in self.department.courses:
                if (course.courseType != courseType
                        or not course.hasCapacity()
                        or self.hasCourseOverlap(course, False)
                        or (self.semester < course.semester() and self.transcript.cgpa < 3)
                        or course in self.draft
                        or not self.checkThePrerequisiteAndCourseThatWasTaken(course)
                        or (course.semester() % 2 != self.semester % 2)):
                    continue
                else:
                    self.availableCoursesToAdd.append(course)

    def addFacultyTechnicalElective(self):
        if self.maxCoursesReached():
            return
        self.computeAvailableTEAndFTECourses("FTE")

        if not self.availableCoursesToAdd:
            ConsoleColours.paintRedMenu()
            print("You do not have any addable course!")
        else:
            self.printMenu("addCourse")
            for i, course in enumerate(self.availableCoursesToAdd):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00")
                print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.availableCoursesToAdd)} to add course: \n")
            userNumberInput1 = CourseRegistrationSystem().getInput()

            if 1 <= userNumberInput1 <= len(self.availableCoursesToAdd):
                self.draft.append(self.availableCoursesToAdd[userNumberInput1 - 1])
                logging.info(
                    f"Student {self.ID} added {self.availableCoursesToAdd[userNumberInput1 - 1].courseCode} to draft.")
                del self.availableCoursesToAdd[userNumberInput1 - 1]
                if self.availableCoursesToAdd:
                    self.addFacultyTechnicalElective()
            elif userNumberInput1 > len(self.availableCoursesToAdd) or userNumberInput1 < 0:
                ConsoleColours.paintRedMenu()
                print("Invalid input, please enter a valid number")
                self.addFacultyTechnicalElective()
            else:
                return

    def addNonTechnicalElective(self):
        if self.maxCoursesReached():
            return
        self.computeAvailableNTECourses()

        if not self.availableCoursesToAdd:
            ConsoleColours.paintRedMenu()
            print("You do not have any addable course!")
        else:
            self.printMenu("addCourse")
            for i, course in enumerate(self.availableCoursesToAdd):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00")
                print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.availableCoursesToAdd)} to add course: \n")
            userNumberInput1 = CourseRegistrationSystem().getInput()

            if 1 <= userNumberInput1 <= len(self.availableCoursesToAdd):
                self.draft.append(self.availableCoursesToAdd[userNumberInput1 - 1])
                logging.info(
                    f"Student {self.ID} added {self.availableCoursesToAdd[userNumberInput1 - 1].courseCode} to draft.")
                del self.availableCoursesToAdd[userNumberInput1 - 1]
                if self.availableCoursesToAdd:
                    self.addNonTechnicalElective()
            elif userNumberInput1 > len(self.availableCoursesToAdd) or userNumberInput1 < 0:
                ConsoleColours.paintRedMenu()
                print("Invalid input, please enter a valid number")
                self.addNonTechnicalElective()
            else:
                return

    def computeAvailableNTECourses(self):
        self.availableCoursesToAdd.clear()

        for course in self.department.courses:
            if (course.courseType != "NTE"
                    or not course.hasCapacity()
                    or self.hasCourseOverlap(course, False)
                    or (self.semester < course.semester() and self.transcript.cgpa < 3)
                    or course in self.draft
                    or not self.checkThePrerequisiteAndCourseThatWasTaken(course)
                    or (course.semester() % 2 != self.semester % 2)):
                continue
            else:
                self.availableCoursesToAdd.append(course)

    def computeAvailableMandatoryCourses(self):
        self.availableCoursesToAdd.clear()

        for course in self.department.courses:
            if course.courseCode == "CSE4297":
                sum = 0
                for studentCourse in self.transcript.studentCourses:
                    if (studentCourse.semester() > 6 and
                            (course.courseType in ["NTE", "TE", "FTE"])
                            or studentCourse.courseCode in ["ISG121", "ISG122"]
                            or self.transcript.courseGradeMap[studentCourse][-1] is None
                            or self.transcript.courseGradeMap[studentCourse][-1].letterGrade > "DD"):
                        continue
                    sum += course.courseCredit

                if (sum < 165
                        or course.courseType != "Mandatory"
                        or not course.hasCapacity()
                        or self.hasCourseOverlap(course, False)
                        or (self.semester < course.semester() and self.transcript.cgpa < 3)
                        or course in self.draft
                        or not self.checkThePrerequisiteAndCourseThatWasTaken(course)
                        or (course.semester() % 2 != self.semester % 2)):
                    continue
                else:
                    self.availableCoursesToAdd.append(course)
            else:
                if (course.courseType != "Mandatory"
                        or not course.hasCapacity()
                        or self.hasCourseOverlap(course, False)
                        or (self.semester < course.semester() and self.transcript.cgpa < 3)
                        or course in self.draft
                        or not self.checkThePrerequisiteAndCourseThatWasTaken(course)
                        or (course.semester() % 2 != self.semester % 2)):
                    continue
                else:
                    self.availableCoursesToAdd.append(course)

    def addCourseToDrop(self):
        self.computeAvailableCoursesToDrop()

        if not self.availableCoursesToDrop:
            ConsoleColours.paintRedMenu()
            print("You do not have any droppable course!")
        else:
            self.printMenu("dropCourse")
            for i, course in enumerate(self.availableCoursesToDrop):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00")
                print("´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")

            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.availableCoursesToDrop)} to add course: \n")
            userNumberInput = CourseRegistrationSystem().getInput()

            if 1 <= userNumberInput <= len(self.availableCoursesToDrop):
                self.draft.append(self.availableCoursesToDrop[userNumberInput - 1])
                logging.info(
                    f"Student {self.ID} added {self.availableCoursesToDrop[userNumberInput - 1].courseCode} to draft.")
                del self.availableCoursesToDrop[userNumberInput - 1]
                if self.availableCoursesToDrop:
                    self.addCourseToDrop()
            elif userNumberInput > len(self.availableCoursesToDrop) or userNumberInput < 0:
                ConsoleColours.paintRedMenu()
                print("Invalid input, please enter a valid number")
                self.addCourseToDrop()

    def checkThePrerequisiteAndCourseThatWasTaken(self, course):
        mapGrade = self.transcript.courseGradeMap

        if course not in mapGrade:
            status = True
            for prerequisite in course.preRequisiteCourses:
                if (prerequisite not in self.transcript.studentCourses
                        or mapGrade[prerequisite][-1] is None
                        or mapGrade[prerequisite][-1].letterGrade in ["FF", "FD"]):
                    return False
        elif not (mapGrade[course][-1] is not None and mapGrade[course][-1].letterGrade > "CC"):
            return False

        return True

    def hasCourseOverlap(self, courseToAdd, isLab):
        i, j = 0, 0
        day_to_index = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4}
        hour_to_index = {8: 0, 9: 1, 10: 2, 11: 3, 13: 4, 14: 5, 15: 6, 16: 7}

        i = day_to_index[courseToAdd.day]
        j = hour_to_index[courseToAdd.hour]

        courseInSchedule = self.schedule[i][j]

        if isLab:
            if courseInSchedule:
                scheduleCourseGrades = self.transcript.courseGradeMap[courseInSchedule]
                if scheduleCourseGrades and not scheduleCourseGrades[-1]:
                    return True
                elif len(scheduleCourseGrades) >= 2 and scheduleCourseGrades[-2].letterGrade == "DZ":
                    return True
            for course in self.draft:
                if self.transcript.courseGradeMap[course] and not self.transcript.courseGradeMap[course][-1]:
                    continue
                if course.day == courseToAdd.day and course.hour == courseToAdd.hour:
                    if self.transcript.courseGradeMap[course] and self.transcript.courseGradeMap[course][
                        -1].letterGrade == "DZ":
                        return True
            for labSection in self.labSections:
                if labSection.day == courseToAdd.day and labSection.hour == courseToAdd.hour:
                    return True
        else:
            if courseInSchedule:
                scheduleCourseGrades = self.transcript.courseGradeMap[courseInSchedule]
                if scheduleCourseGrades and not scheduleCourseGrades[-1]:
                    return True
                if scheduleCourseGrades and len(scheduleCourseGrades) >= 2 and scheduleCourseGrades[
                    -2].letterGrade == "DZ":
                    if not courseToAdd in self.transcript.studentCourses or (
                            self.transcript.courseGradeMap[courseToAdd][-1] and
                            self.transcript.courseGradeMap[courseToAdd][-1].letterGrade == "DZ"):
                        return True
            for course in self.draft:
                if self.transcript.courseGradeMap[course] and not self.transcript.courseGradeMap[course][-1]:
                    continue
                if course.day == courseToAdd.day and course.hour == courseToAdd.hour:
                    if self.transcript.courseGradeMap[course] and self.transcript.courseGradeMap[course][
                        -1].letterGrade == "DZ":
                        return True
                    if not self.transcript.courseGradeMap[course] and (
                            not self.transcript.courseGradeMap[courseToAdd] or
                            self.transcript.courseGradeMap[courseToAdd][-1].letterGrade == "DZ"):
                        return True
            for labSection in self.labSections:
                if labSection.day == courseToAdd.day and labSection.hour == courseToAdd.hour:
                    return True
        return False

    def maxCoursesReached(self):
        numberOfCourses = self.calculateNumberOfCourses()
        if numberOfCourses >= self.department.maxCourseNumber:
            ConsoleColours.paintRedMenu()
            logging.warning(f"Student {self.ID} reached the maximum number of courses.")
            print(f"Limit Reached! You can take at most {self.department.maxCourseNumber} courses.")
            return True
        return False

    def calculateNumberOfCourses(self):
        numberOfCourses = 0
        numberOfCourses += len(self.draft)

        for course in self.transcript.courseGradeMap.keys():
            if self.transcript.courseGradeMap[course][-1] is None:
                numberOfCourses += 1
        return numberOfCourses

    def removeCourseFromDraft(self):
        if self.hasRequest:
            ConsoleColours.paintRedMenu()
            logging.warning(
                f"Student {self.ID} can not remove lecture because he/she has a request waiting for {self.advisor.ID}'s approval.")
            print("You can not remove lecture because you have a request waiting for approval.")
            return;
        if not self.draft:
            ConsoleColours.paintRedMenu()
            print("Your draft is empty!")
            ConsoleColours.resetColour()
        else:
            ConsoleColours.paintBlueMenu()
            print(
                "Remove Course from Draft\n¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\nBack -> 0\n¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨\nSelect the course you want to remove:\n¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
            ConsoleColours.paintPurpleMenu()
            for i, course in enumerate(self.draft):
                print(
                    f"{i + 1}. {course.courseCode} - {course.courseName} - {course.day} - {course.hour}.00\n´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´")
            ConsoleColours.paintGreenMenu()
            print(f"Choose number between 1 to {len(self.draft)} to remove course: \n")
            userNumberInput = CourseRegistrationSystem().getInput()
            if 1 <= userNumberInput <= len(self.draft):
                for labSection in self.draft[userNumberInput - 1].laboratorySections:
                    if labSection in self.labSections:
                        self.labSections.remove(labSection)
                        labSection.setNumberOfStudents(labSection.numberOfStudents - 1)
                        break
                logging.info(
                    f"Student {self.ID} removed {self.draft[userNumberInput - 1].courseCode} from draft.")
                del self.draft[userNumberInput - 1]
                self.removeCourseFromDraft()
            elif userNumberInput > len(self.draft) or userNumberInput < 0:
                ConsoleColours.paintRedMenu()
                logging.warning(f"Student {self.ID} entered invalid input.")
                print("Invalid input, please enter a number")
                self.removeCourseFromDraft()

    def showRequestStatus(self):
        ConsoleColours.paintYellowMenu()
        if self.hasRequest:
            logging.info(f"Student {self.ID} has a request waiting for {self.advisor.ID}'s approval.")
            print("Your request is waiting for advisor approval.")
        else:
            print("There is no waiting request.")
            ConsoleColours.resetColour()
            return

        ConsoleColours.paintBlueMenu()
        print(":::::::::::::::::::::::Your draft:::::::::::::::::::::::")
        ConsoleColours.paintGreenMenu()
        print(
            "::::::Courses that have been requested to be added::::::\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n")
        for course in self.draft:
            if not (self.transcript.courseGradeMap[course] and
                    self.transcript.courseGradeMap[course][-1] is None):
                print(f"{course.courseCode}-{course.courseName}")
        print("\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n")
        ConsoleColours.paintRedMenu()
        print(
            ":::::Courses that have been requested to be dropped:::::\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n")
        for course in self.draft:
            if self.transcript.courseGradeMap[course] and self.transcript.courseGradeMap[course][
                -1] is None:
                print(f"{course.courseCode}-{course.courseName} - {course.day} - {course.hour}")
        print("\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n")
        ConsoleColours.resetColour()
        print("\n")

    def computeAvailableCoursesToDrop(self):
        self.availableCoursesToDrop.clear()
        mapGrade = self.transcript.courseGradeMap
        for course in self.transcript.studentCourses:
            if course in self.draft:
                continue
            if mapGrade[course][-1] is None:
                self.availableCoursesToDrop.append(course)

    def login(self, userName, password):
        return self.userName == userName and self.password == password
