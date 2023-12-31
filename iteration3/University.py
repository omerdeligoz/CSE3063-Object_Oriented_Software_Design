class University:
    def __init__(self, name):
        self.name = name
        self.departments = []  # it holds departments list.
        self.userNamePersonMap = {}  # it holds username and password of users.

    def getDepartments(self):
        return self.departments

    def getUserNamePersonMap(self):
        return self.userNamePersonMap