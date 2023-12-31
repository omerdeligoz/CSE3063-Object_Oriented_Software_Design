from abc import ABC, abstractmethod

class Person(ABC):
    def __init__(self, ID, name, surname, userName, password, department=None):
        self.ID = ID
        self.name = name
        self.surname = surname
        self.userName = userName
        self.password = password
        self.department = department

    @abstractmethod
    def login(self, userName, password):
        pass

    def getUserName(self):
        return self.userName

    def setUserName(self, userName):
        self.userName = userName

    def getPassword(self):
        return self.password

    def setPassword(self, password):
        self.password = password

    def getDepartment(self):
        return self.department

    def setDepartment(self, department):
        self.department = department

    def getName(self):
        return self.name

    def getSurname(self):
        return self.surname

    def getID(self):
        return self.ID