import CourseRegistrationSystem
from Notification import Notification


class Registration:

    def __init__(self, student, courses):
        self.__student = student
        self.__courses = courses

    def approveRequest(self):
        print("Request approved.")
        system = CourseRegistrationSystem.CourseRegistrationSystem()
        for course in self.__courses:
            if course not in self.__student.getTranscript().getStudentCourses():
                system.addToSchedule(course, self.__student)
                self.__student.getTranscript().getCourseGradeMap()[course] = [None]
                self.__student.getTranscript().getStudentCourses().append(course)
                course.setNumberOfStudents(course.getNumberOfStudents() + 1)
            else:
                tempList = self.__student.getTranscript().getCourseGradeMap()[course]

                if len(tempList) == 1 and tempList[0] is None:
                    system.removeFromSchedule(course, self.__student)
                    self.__student.getTranscript().getCourseGradeMap().pop(course, None)
                    self.__student.getTranscript().getStudentCourses().remove(course)
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1)
                elif len(tempList) == 1 and tempList[0] is not None:
                    system.addToSchedule(course, self.__student)
                    tempList.append(None)
                    course.setNumberOfStudents(course.getNumberOfStudents() + 1)
                elif len(tempList) > 1 and tempList[-1] is None:
                    system.removeFromSchedule(course, self.__student)
                    tempList.remove(tempList[-1])
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1)
                elif tempList and tempList[-1] is not None:
                    system.addToSchedule(course, self.__student)
                    tempList.append(None)
                    course.setNumberOfStudents(course.getNumberOfStudents() + 1)

        self.__student.setHasRequest(False)
        self.__student.getDraft().clear()
        Notification(self.__student, "Your request has been approved.").sendNotification()

    def rejectRequest(self):
        print("Request rejected.")

        self.__student.setHasRequest(False)
        self.__student.getDraft().clear()

        Notification(self.__student, "Your request has been rejected.").sendNotification()

    def addRequest(self, advisor):
        advisor.requests.append(self)
        self.__student.setHasRequest(True)
        print("Request sent to advisor.")

    def getStudent(self):
        return self.__student

    def getCourses(self):
        return self.__courses
