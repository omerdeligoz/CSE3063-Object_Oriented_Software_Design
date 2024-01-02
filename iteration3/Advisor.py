import logging

import CourseRegistrationSystem
from IDisplayMenu import IDisplayMenu
from Lecturer import Lecturer


class Advisor(Lecturer, IDisplayMenu):

    def __init__(self, ID, name, surname, userName, password):
        super().__init__(ID, name, surname, userName, password)
        self.__studentsAdvised = []
        self.__requestList = []
        self.__requestNumber = None
        self.__notification = None

    def printRequests(self):
        controller = CourseRegistrationSystem.CourseRegistrationSystem()
        print("Request Approval Menu:")
        print("\nBack -> 0\n")
        if len(self.__requestList) != 0:
            print("Request(s) Listed Below:")
            for i in range(len(self.__requestList)):
                print(
                    f"{i + 1}. {self.__requestList[i].getStudent().getName()} {self.__requestList[i].getStudent().getSurname()}")
                print("``````````````````````````````````````````````")
            requestNumber = controller.getInput()
            if 1 <= requestNumber <= len(self.__requestList):
                self.__requestNumber = requestNumber
                request = self.__requestList[requestNumber - 1]
                print(
                    f"{request.getStudent().getName()} {request.getStudent().getSurname()} wants to take these courses:")
                for course in request.getCourses():
                    if not (request.getStudent().getTranscript().getCourseGradeMap().get(course) is not None
                            and request.getStudent().getTranscript().getCourseGradeMap().get(course)[-1] is None):
                        print(f"{course.getCourseCode()} {course.getCourseName()}")
                print(
                    f"{request.getStudent().getName()} {request.getStudent().getSurname()} wants to drop these courses:")
                for course in request.getCourses():
                    if request.getStudent().getTranscript().getCourseGradeMap().get(course) is not None \
                            and request.getStudent().getTranscript().getCourseGradeMap().get(course)[-1] is None:
                        print(f"{course.getCourseCode()} {course.getCourseName()}")
                self.replyRequests()
            elif requestNumber > len(self.__requestList) or requestNumber < 0:
                print("Invalid choice! Please select again.")
                self.printRequests()
        else:
            print("There is not a waiting request!")
            print("You are redirecting to the Advisor Main Menu...")

    def replyRequests(self):
        controller = CourseRegistrationSystem.CourseRegistrationSystem()
        print("               Back -> 0")
        print("    Approve Request -> 1")
        print("     Reject Request -> 2")
        print("Please select an option:")

        choice = controller.getInput()
        if choice == 1:
            self.__requestList[self.__requestNumber - 1].approveRequest()
            logging.info(
                f"Advisor {self.getID()} approved request of student {self.__requestList[self.__requestNumber - 1].getStudent().getID()}")
            self.__requestList.pop(self.__requestNumber - 1)
            self.printRequests()
        elif choice == 2:
            self.__requestList[self.__requestNumber - 1].rejectRequest()
            logging.info(
                f"Advisor {self.getID()} rejected request of student {self.__requestList[self.__requestNumber - 1].getStudent().getID()}")
            self.__requestList.pop(self.__requestNumber - 1)
            self.printRequests()
        elif choice == 0:
            self.printRequests()
        else:
            logging.warning(f"Advisor {self.getID()} entered invalid input.")
            print("Invalid choice. Please select again!")
            self.replyRequests()

    def printMenu(self, menuType):
        print(f"Welcome {self.getName()} {self.getSurname()}!")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

        message = f"You have {len(self.__requestList)} request(s)."
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
        return self.getUserName() == userName and self.getPassword() == password

    def getStudentsAdvised(self):
        return self.__studentsAdvised

    def getRequestList(self):
        return self.__requestList

    def setNotification(self, notification):
        self.__notification = notification
