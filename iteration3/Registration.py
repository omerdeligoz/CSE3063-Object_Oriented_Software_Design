import CourseRegistrationSystem
import Notification


class Registration:

    def __init__(self, student, courses):
        self.student = student
        self.courses = courses

    def approveRequest(self):
        print("Request approved.")

        for course in self.courses:
            if course not in self.student.transcript.studentCourses:
                CourseRegistrationSystem.addToSchedule(course, self.student)
                self.student.transcript.courseGradeMap[course] = [None]
                self.student.transcript.studentCourses.append(course)
                course.numberOfStudents += 1
            else:
                tempList = self.student.transcript.courseGradeMap[course]

                if len(tempList) == 1 and tempList[0] is None:
                    system.removeFromSchedule(course, self.student)
                    self.student.transcript.courseGradeMap.pop(course, None)
                    self.student.transcript.studentCourses.remove(course)
                    course.numberOfStudents -= 1
                elif len(tempList) == 1 and tempList[0] is not None:
                    system.addToSchedule(course, self.student)
                    tempList.append(None)
                    course.numberOfStudents += 1
                elif len(tempList) > 1 and tempList[-1] is None:
                    system.removeFromSchedule(course, self.student)
                    tempList.remove(tempList[-1])
                    course.numberOfStudents -= 1
                elif tempList and tempList[-1] is not None:
                    system.addToSchedule(course, self.student)
                    tempList.append(None)
                    course.numberOfStudents += 1

        self.student.hasRequest = False
        self.student.draft.clear()
        Notification(self.student, "Your request has been approved.").sendNotification()

    def rejectRequest(self):
        print("Request rejected.")

        self.student.hasRequest = False
        self.student.draft.clear()

        Notification(self.student, "Your request has been rejected.").sendNotification()

    def addRequest(self, advisor):
        advisor.requests.append(self)
        self.student.hasRequest = True
        print("Request sent to advisor.")

    def getStudent(self):
        return self.student

    def getCourses(self):
        return self.courses