class Department:
    max_course_number = 10

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
