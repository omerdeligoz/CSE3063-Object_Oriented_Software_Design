class Course:
    def __init__(self, courseName, courseCode, courseType, courseCredit, semester, capacity, hour, day):
        self.courseName = courseName
        self.courseCode = courseCode
        self.courseType = courseType
        self.capacity = capacity
        self.courseCredit = courseCredit
        self.semester = semester
        self.hour = hour
        self.day = day
        self.laboratorySections = []
        self.preRequisiteCourses = []
        self.numberOfStudents = 0
        self.lecturer = None
