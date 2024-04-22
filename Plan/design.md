# Your Design

---
- What classes do I need? Would it be helpful to have any interfaces or abstract classes and for what?
    - See classes and methods below.
---

## For each Interface

- What are the promised actions (methods) of anything that implements this interface?
- What are the parameters needed?
- What will they return?

    See below for interface DatabaseItem.
---

## For each Abstract class

- Does this class need to implement any interfaces or extend any classes? Any you wrote or others.
- What is the information (instance variables) that this class has that will be similar across child classes?
- What are the types, should they have any default values?
- What are the promised actions (methods) for this class?
- What are the parameters needed?
- What will they return?
- Which of these should be concrete and which abstract?

    Abstract person implements DatabaseItem, however addToDatabase and updateFromDatebase are abstract and need to be implemented by children.

    Everything else can be found in the class plan below.

---

## For each class

- Does this class need to implement any interfaces or extend any classes? Any you wrote or others.
- What is the information (instance variables) that this class has?
- What are the types?
- What are the actions (methods) for this class? Which of these methods do other classes need access to?
- What are the parameters needed?
- What will they return?

    See class plan below for this information.

---
## Testing for each method


- What are your test cases that you need and what inputs and outputs will you test for each?

    - For every database item, save the object to a test DB, read it back to create an object, and confirm object equality.
    - For database, ensure singleton behavior works correctly and does not create new instance.
    - For the DB accesser methods in Person, add people then ensure they are retrievable.
    - For frontend classes for methods that dont directly serve a webpage, mock db and form inputs and ensure objects are created correctly.
    - For Ticket and its related classes, ensure that all the update and notify functions update the proper instance fields such as approver chain, iterator, comments, etc. For generateApproverChain, ensure it creates appropriate approvers by tracing up the departmental chain of a person. For makeRecommendation, give it a ticket and make sure the ticket is edited accordingly. For cancelTicket, make sure only the requestor who made the ticket can cancel the ticket.  


---
## All Classes and Methods Plan:

For all classes with instance variables, public getters and protected setters
    - Backend

        - interface DatabaseItem
            - boolean addToDatabase();
            - boolean removeFromDatabase();
            - boolean updateFromDatabase(String query);
            - Optional<DatabaseItem> fetchFromDatabase(int id);
            - List<DatabaseItem> queryDatabase(String query);
        - Database (singleton)
            - Database getInstance();
        - abstract class Person implements DatabaseItem
            - private int id;
            - private String firstName;
            - private String lastName
            - private Date birthdate;
            - private String phoneNumber;
            - private String username;
            - private String organizationEmail;
            - private String secondaryEmail;
            - private boolean isActive;
            - private Department department;
            - public static List<Person> getAllWithFirstName(String firstName)
            - public static List<Person> getAllWithLastName(String lastName)
            - public static List<Person> getByPhoneNumber(String phoneNumber)
            - public static Optional<Person> getByUsername(String username)
            - public static Optional<Person> getByOrganizationEmail(String organizationEmail)
            - public static Optional<Person> getBySecondaryEmail(String secondaryEmail)
            - public static List<Person> getActivePeople()
            - public static List<Person> getInactivePeople()
        - class Employee extends person
            - private Optional<Employee> manager;
            - private Date startDate;
            - private double hourlyWage;
        - class Faculty extends Employee
            - private Room officeLocation;
            - private Department department;
            - private boolean hasTenure;
            - private College college;
        - class Student extends Person
            - private String major;
            - private Grade grade;
        - enum Grade
            - FRESHMAN
            - SOPHOMORE
            - JUNIOR
            - SENIOR
            - GRADUATE
            - DOCTORAL
        - class Alumni extends Person
            - private Date graduationDate;
            - private Degree degreeType;
        - enum Degree
            - BACHELOR
            - MASTERS
            - DOCTORAL
        - class Building implements DatabaseItem
            - private String name;
            - private String address;
            - private int floors;
            - private String abbreviation;
        - class Room implements DatabaseItem
            - private Building building;
            - private int roomNumber;
        - class Department implements DatabaseItem
            - private String name;
            - public Faculty[] getFacultyInDepartment();
            - public Student[] getStudentsInDepartment();
            - public Alumni[] getAlumniInDepartment();
            - public Person[] getAllInDepartment();
    - Frontend
        - WebAppDriver
            - public static void main(String args[]);
        - DashboardController
            - public String renderDashboard()
            - protected String renderSidebar()
            - protected String renderDataPanels()
        - PersonFormController implements WebMVCConfigurer
            - Public void addViewControllers(ViewControllerRegistry registry);
            - public String form(Person personForm);
            - public String addPerson(@Valid Person personForm, BindingResult result);
        - DepartmentFormController implements WebMVCConfigurer
            - public void addViewControllers(ViewControllerRegistry registry);
            - public String form(Department departmentForm);
            - public String addPerson(@Valid Department departmentForm, BindingResult result);
        - BuildingFormController implements WebMVCConfigurer
            - Public void addViewControllers(ViewControllerRegistry registry);
            - public String form(Building buildingForm);
            - public String addPerson(@Valid Building buildingForm, BindingResult result);
        - RoomFormController implements WebMVCConfigurer
            - Public void addViewControllers(ViewControllerRegistry registry);
            - public String form(Room roomForm);
            - public String addPerson(@Valid Room roomForm, BindingResult result);
        - BulkDataUploadController implements WebMVCConfigurer
            - Public void addViewControllers(ViewControllerRegistry registry);
            - public String form(Model model) throws IOException;
            - public String processForm(@RequestParam(“file”) MultipartFile file, RedirectAttributes redirectAttributes);
            - public void parseCSV(List<String> lines);
            - public DatabaseItem parseLineItem(String line);
        - TableVisualizer
            - public String generateAlignedBox(List<DatabaseItem> items);

