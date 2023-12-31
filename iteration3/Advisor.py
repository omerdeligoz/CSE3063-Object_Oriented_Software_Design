import logging

import CourseRegistrationSystem
from IDisplayMenu import IDisplayMenu
from Lecturer import Lecturer


class Advisor(Lecturer, IDisplayMenu):

    def __init__(self, ID, name, surname, userName, password):
        super().__init__(ID, name, surname, userName, password)
        self.studentsAdvised = []
        self.requestList = []
        self.requestNumber = None
        self.notification = None

    def printRequests(self):
        controller = CourseRegistrationSystem
        print("Request Approval Menu:")
        print("\nBack -> 0\n")
        if len(self.requestList) != 0:
            print("Request(s) Listed Below:")
            for i in range(len(self.requestList)):
                print(f"{i + 1}. {self.requestList[i].student.name} {self.requestList[i].student.surname}")
                print("``````````````````````````````````````````````")
            requestNumber = controller.getInput()
            if 1 <= requestNumber <= len(self.requestList):
                request = self.requestList[requestNumber - 1]
                print(f"{request.student.name} {request.student.surname} wants to take these courses:")
                for course in request.courses:
                    if not (request.student.transcript.courseGradeMap.get(course) is not None
                            and request.student.transcript.courseGradeMap.get(course).last is None):
                        print(f"{course.courseCode} {course.courseName}")
                print(f"{request.student.name} {request.student.surname} wants to drop these courses:")
                for course in request.courses:
                    if request.student.transcript.courseGradeMap.get(course) is not None \
                            and request.student.transcript.courseGradeMap.get(course).last is None:
                        print(f"{course.courseCode} {course.courseName}")
                self.replyRequests()
            elif requestNumber > len(self.requestList) or requestNumber < 0:
                print("Invalid choice! Please select again.")
                self.printRequests()
        else:
            print("There is not a waiting request!")
            print("You are redirecting to the Advisor Main Menu...")

    def replyRequests(self):
        controller = CourseRegistrationSystem()
        print("               Back -> 0")
        print("    Approve Request -> 1")
        print("     Reject Request -> 2")
        print("Please select an option:")

        choice = controller.getInput()
        if choice == 1:
            self.requestList[self.requestNumber - 1].approveRequest()
            logging.info(
                f"Advisor {self.ID} approved request of student {self.requestList[self.requestNumber - 1].student.ID}")
            self.requestList.pop(self.requestNumber - 1)
            self.printRequests()
        elif choice == 2:
            self.requestList[self.requestNumber - 1].rejectRequest()
            logging.info(
                f"Advisor {self.ID} rejected request of student {self.requestList[self.requestNumber - 1].student.ID}")
            self.requestList.pop(self.requestNumber - 1)
            self.printRequests()
        elif choice == 0:
            self.printRequests()
        else:
            logging.warning(f"Advisor {self.ID} entered invalid input.")
            print("Invalid choice. Please select again!")
            self.replyRequests()

    def printMenu(self, menuType):
        print(f"Welcome {self.name} {self.surname}!")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

        message = f"You have {len(self.requestList)} request(s)."
        print(message)
        print()

        print("Please select from the following options:")
        print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
        print("                                Exit -> 0")
        print("                       List requests -> 1")
        print("                             Log out -> 2")
        print("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")

        print("Enter your choice: ")

    def login(self, userName, password):
        return self.userName == userName and self.password == password
