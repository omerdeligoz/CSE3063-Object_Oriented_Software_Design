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
            if course not in self.getStudent().getTranscript().getStudentCourses():
                system.addToSchedule(course, self.getStudent())
                self.getStudent().getTranscript().getCourseGradeMap()[course] = [None]
                self.getStudent().getTranscript().getStudentCourses().append(course)
                course.setNumberOfStudents(course.getNumberOfStudents() + 1)
            else:
                tempList = self.getStudent().getTranscript().getCourseGradeMap()[course]

                if len(tempList) == 1 and tempList[0] is None:
                    system.removeFromSchedule(course, self.getStudent())
                    self.getStudent().getTranscript().getCourseGradeMap().pop(course, None)
                    self.getStudent().getTranscript().getStudentCourses().remove(course)
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1)
                elif len(tempList) == 1 and tempList[0] is not None:
                    system.addToSchedule(course, self.getStudent())
                    tempList.append(None)
                    course.setNumberOfStudents(course.getNumberOfStudents() + 1)
                elif len(tempList) > 1 and tempList[-1] is None:
                    system.removeFromSchedule(course, self.getStudent())
                    tempList.remove(tempList[-1])
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1)
                elif tempList and tempList[-1] is not None:
                    system.addToSchedule(course, self.getStudent())
                    tempList.append(None)
                    course.setNumberOfStudents(course.getNumberOfStudents() + 1)

        self.getStudent().setHasRequest(False)
        self.getStudent().getDraft().clear()
        Notification(self.getStudent(), "Your request has been approved.").sendNotification()

    def rejectRequest(self):
        print("Request rejected.")

        self.getStudent().setHasRequest(False)
        self.getStudent().getDraft().clear()

        Notification(self.getStudent(), "Your request has been rejected.").sendNotification()

    def addRequest(self, advisor):
        advisor.getRequestList().append(self)
        self.getStudent().setHasRequest(True)
        print("Request sent to advisor.")

    def getStudent(self):
        return self.__student

    def getCourses(self):
        return self.__courses
