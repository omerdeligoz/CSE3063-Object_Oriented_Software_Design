class Department:

    def __init__(self, departmentName: str):
        self.departmentName = departmentName
        self.advisors = []
        self.transcripts = []
        self.courses = []
        self.students = []
        self.laboratorySections = []
        self.lecturers = []
        self.assistants = []
        self.courseCodeCourseMap = {}
        self.sectionCodeCourseMap = {}
        self.labSectionCourseMap = {}
        self.studentIDStudentMap = {}
        self.advisorIDAdvisorMap = {}
        self.__maxCourseNumber = 10

    def getDepartmentName(self):
        return self.departmentName

    def getAdvisors(self):
        return self.advisors

    def getTranscripts(self):
        return self.transcripts

    def getCourses(self):
        return self.courses

    def getStudents(self):
        return self.students

    def getLaboratorySections(self):
        return self.laboratorySections

    def getLecturers(self):
        return self.lecturers

    def getAssistants(self):
        return self.assistants

    def getCourseCodeCourseMap(self):
        return self.courseCodeCourseMap

    def getSectionCodeCourseMap(self):
        return self.sectionCodeCourseMap

    def getLabSectionCourseMap(self):
        return self.labSectionCourseMap

    def getStudentIDStudentMap(self):
        return self.studentIDStudentMap

    def getAdvisorIDAdvisorMap(self):
        return self.advisorIDAdvisorMap

    def getMaxCourseNumber(self):
        return self.__maxCourseNumber
