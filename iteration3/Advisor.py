import logging

from iteration3.IDisplayMenu import IDisplayMenu
from iteration3.Lecturer import Lecturer


class Advisor(Lecturer, IDisplayMenu):

    def __init__(self, ID, name, surname, userName, password):
        super().__init__(ID, name, surname, userName, password)
        from iteration3.CourseRegistrationSystem import CourseRegistrationSystem
        self.__system = CourseRegistrationSystem()
        self.__studentsAdvised = []
        self.__requestList = []
        self.__requestNumber = None
        self.__notification = None

    def printRequests(self):
        print("Request Approval Menu:")
        print("\nBack -> 0\n")
        if len(self.__requestList) != 0:
            print("Request(s) Listed Below:")
            for i in range(len(self.__requestList)):
                print(
                    f"{i + 1}. {self.__requestList[i].getStudent().getName()} {self.__requestList[i].getStudent().getSurname()}")
                print("``````````````````````````````````````````````")
            requestNumber = self.__system.getInput()
            if 1 <= requestNumber <= len(self.__requestList):
                self.__requestNumber = requestNumber
                request = self.__requestList[requestNumber - 1]
                print(
                    f"{request.getStudent().getName()} {request.getStudent().getSurname()} wants to take these courses:")
                sorted_courses = []
                nested_list = []
                for course in request.getCourses():
                    sorted_courses.append(course.getCourseCode())
                    sorted_courses.append(course.getCourseName())
                    sorted_courses.append(course.getDay())
                    sorted_courses.append(course.getHour())
                    nested_list.append(sorted_courses)
                    sorted_courses = []
                    if not (request.getStudent().getTranscript().getCourseGradeMap().get(course) is not None
                            and request.getStudent().getTranscript().getCourseGradeMap().get(course)[-1] is None):
                        print(f"{course.getCourseCode()} {course.getCourseName()} - {course.getDay()} - {course.getHour()}.00")
                        print()
                lanet = []
                for i in range(len(nested_list) - 1):
                    if i in lanet:
                        continue
                    controlInput = 0
                    for j in range(i + 1, len(nested_list)):
                        if j in lanet:
                            continue
                        first_list = nested_list[i]
                        second_list = nested_list[j]

                        es_Day = first_list[2]
                        es_Hour = first_list[3]

                        g_day = second_list[2]
                        g_Hour = second_list[-1]

                        if (es_Day == g_day) and (es_Hour == g_Hour) and controlInput == 0:
                            print(
                                f"{first_list[0]} - {first_list[1]} / {second_list[0]} - {second_list[1]} /", end='')
                            controlInput = 1
                            lanet.append(j)
                            continue
                        if (es_Day == g_day) and (
                                es_Hour == g_Hour) and controlInput == 1:
                            print(
                                f"{second_list[0]} - {second_list[1]} /",
                                end='')
                            lanet.append(j)
                    print(
                        f"------->{nested_list[i][2]} - {nested_list[i][3]}.00 -----> CLASS OVERLAP!!!")
                print()
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
        print("               Back -> 0")
        print("    Approve Request -> 1")
        print("     Reject Request -> 2")
        print("Please select an option:")

        choice = self.__system.getInput()
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
