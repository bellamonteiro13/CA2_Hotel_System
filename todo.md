# ✅ CA2 Hotel System: To-Do List

> 📅 **Deadline:** Sat 21 Nov 2026, 23:59 (Moodle) · 🎤 **Q&A:** Tue 24 Nov 2026 (mandatory!)
> Tick a box by changing `[ ]` to `[x]` when a task is done.
> 🎤 = topic the lecturer might ask about in the Q&A

---

## 🧰 Step 1: Setup & GitHub
> 💡 Commit after EVERY step (goal: 10–15+ commits)
- [x] Organise the folder: move the teacher's files into `reference/` and put the SDF work into `docs/SDF/`
- [x] Create our own `Applicants_Form.txt` (41 valid names + 2 invalid lines on purpose, to test validation)
- [x] Create a `.gitignore` (ignore `build/`, `dist/`, `nbproject/private/`, `reference/`, `docs/`)
- [x] `git init` + first commit
- [x] Create the GitHub repo and push the project → https://github.com/bellamonteiro13/CA2_Hotel_System
- [ ] Add the lecturer as a **collaborator** on the repo

## 📋 Step 2: Menu with Enums (10 marks)
- [x] Create a `MenuOption` enum matching the SDF use cases: LOAD_STAFF_FILE (UC1), SORT (UC2), SEARCH (UC3), ADD_EMPLOYEE (UC4), DISPLAY_HIERARCHY (UC5), EXIT
- [x] Display the menu by looping through the enum values (`values()`)
- [x] Read the user's choice and turn it into an enum value (`fromMenuNumber`)
- [x] Validate the choice: letters, empty input and out-of-range numbers show an error without crashing (NFR5)
- [x] Use a `switch` on the enum to call the right feature (placeholders for now: "coming soon!")
- [x] Keep the program looping until the user picks EXIT (`do-while`)
- [ ] Run it in NetBeans yourself and try to break it 😈
- [ ] 🎤 Be able to explain: what is an enum, and why is it better than plain ints or Strings?
- [x] Commit ✔️ (94f889a)

## 🏗️ Step 3: Classes & Inheritance (core requirement)
> Names come from your SDF document, so the code and the diagrams match! 🎯
- [x] Create the `ManagerType` enum (General Manager, Department Manager, Supervisor), written by me! ✍️
- [x] Create the `DepartmentName` enum (Front Office, Housekeeping, Food & Beverage), written by me! ✍️
- [x] Parent class 1: `Employee` (name, designation, department)
  - [x] Subclasses: `Receptionist`, `RoomAttendant`, `Bartender` (receive only the name, build their own department with `new`)
- [x] Parent class 2: `Manager` (abstract, has-a Department), written by me! ✍️
  - [x] Subclasses: `GeneralManager`, `DepartmentManager`, `Supervisor` (receive 2, pass 3 with `super`)
- [x] Parent class 3: `Department` (abstract), written by me! ✍️
  - [x] Subclasses: `FrontOffice`, `Housekeeping`, `FoodAndBeverage` (using `extends` + `super`)
- [x] Constructors + getters in every class
- [x] Descriptive names + comments everywhere (in my own voice 💬)
- [x] `toString()` in `Manager` = **method overriding** (added in Step 6 to print name | type | department; `Employee` gets one in Step 8)
- [ ] 🎤 Be able to explain: `extends`, `super()`, parent vs child class, method overriding
- [x] Commit ✔️ (24ae855, 580797e, 91fc575)

## 📂 Step 4: Read File + Recursive SORT (25 marks)
- [x] Ask the user for the filename and read `Applicants_Form.txt` (UC1: if the file isn't found, ask again)
- [x] Skip invalid lines with a warning, then show how many staff were loaded (UC1)
- [x] If the list is empty, show "Please load the staff file first" (UC2)
- [x] Store the names in a list
- [x] Implement a **RECURSIVE** sorting algorithm by hand: **Quick Sort**, based on the teacher's `QuickSort.java` (no `Collections.sort`!)
- [x] Adapt it from numbers (`int`) to names (`String`, using `compareToIgnoreCase`)
- [x] Sort the names alphabetically
- [x] Display only the **first 20** sorted names
- [x] Test it with an empty list, 1 name, duplicate names and mixed upper/lower case
- [ ] 🎤 (REVISIT with me, still confusing!) Be able to explain: recursion, base case, pivot, Big-O (average O(n log n), worst O(n²)), why Quick Sort and not Selection Sort (O(n²), not recursive)
- [x] Commit ✔️ (6b5a8be, 1bdfa62)

## 🔍 Step 5: Recursive SEARCH (25 marks) 📍
- [x] Implement a **RECURSIVE** search by hand (e.g. Binary Search) on the sorted list
- [x] The user types a name and the program finds the person
- [x] Return **Manager Type** + **Department**
- [x] Handle "name not found" nicely
- [x] Make the search case-insensitive
- [ ] 🎤 Be able to explain: why the list must be sorted first, O(log n) vs linear search O(n)
- [ ] Commit ✔️

## 🎲 Step 6: Random Data Generation (core requirement)
- [x] Randomly assign a Manager Type + Department to each name from the file
- [x] Make sure the random data is *appropriate* (valid types and departments only, and only ONE General Manager)
- [x] 🎤 Be able to explain: how `Random` works and why the data makes sense
- [x] Commit ✔️ (ba54e63)

## ➕ Step 7: Add New Records + Validation (20 marks)
- [ ] Ask for the **Name** (reject empty input, numbers and symbols)
- [ ] Show the Manager Types and let the user choose (must be a valid option)
- [ ] Show the Departments and let the user choose (must exist)
- [ ] Handle wrong input (letters instead of numbers, out-of-range numbers) without crashing
- [ ] Add the new record to the list (and keep the list sorted)
- [ ] Print a success message, e.g. `"John Joe" has been added as "Assistant Manager" to "Front Desk" successfully!`
- [ ] Display all newly added records
- [ ] 🎤 Be able to explain: how the validation works, `try/catch`, `InputMismatchException`
- [ ] Commit ✔️

## 🌳 Step 8: Binary Tree Employee Hierarchy (20 marks)
- [ ] Create a `TreeNode` class (employee data + `left` + `right`)
- [ ] Create a `BinaryTree` class
- [ ] Insert at least **20 employees** (Name, Designation, Department)
- [ ] Insert in **level order** so the hierarchy makes sense: General Manager → Department Managers → Supervisors → Employees (max 2 children per node)
- [ ] Include employees added through the menu (UC5)
- [ ] Build a hand-made Queue, like the one in the teacher's `BinaryTreeTeaching.java` (but big enough for 20+ nodes!)
- [ ] Display the tree using **level-order traversal** (BFS with the Queue)
- [ ] Calculate + display the **tree height** (recursive)
- [ ] Calculate + display the **total number of nodes** (recursive)
- [ ] 🎤 Be able to explain: node, root, leaf, height, level-order traversal vs in/pre/post-order, why a Queue
- [ ] Commit ✔️

## 🧪 Step 9: Final Testing & Code Quality
- [ ] Run every menu option from start to finish
- [ ] Try to break it (wrong inputs, empty file, unknown names)
- [ ] Check that every class and method has comments
- [ ] Check that all variable names are descriptive
- [ ] Clean and build in NetBeans: it must **load and run**, or you may get zero!
- [ ] Commit ✔️

## 📝 Step 10: A&C Report (Word document)
- [ ] CCT assessment cover page
- [ ] Section (**max 500 words**) explaining **WHY** we chose this sorting algorithm (not how it works!)
- [ ] Explain **WHY** we chose this searching algorithm (not how it works!)
- [ ] Harvard referencing for any sources
- [ ] GitHub repo link at the end of the report
- [ ] Add the report `.docx` to the GitHub repo + commit ✔️

## 📐 Step 11: SDF Document (PDF, your own work ✍️)
- [x] CCT assessment cover page
- [x] **5 Use Case narratives** (main path + alternative/exception paths) (20 marks)
- [x] Use Case diagram(s) with correct use of `<<include>>` / `<<extend>>`
- [ ] **UML Model 1** (e.g. Class Diagram) (15 marks)
- [ ] **UML Model 2**, a DIFFERENT type (e.g. Activity or Sequence Diagram) (15 marks)
- [ ] Add "future version" features to the diagrams (extra marks!)
- [x] **5 Functional requirements**: why each one is included + priority (e.g. MoSCoW) + justification (30 marks total) (I have 8!)
- [x] **5 Non-functional requirements**: why each one is included + priority + justification (I have 7!)
- [ ] Write the short MoSCoW intro sentence + delete the draft notes in the SDF doc
- [ ] References (Harvard style)
- [ ] **5 User Stories** with acceptance criteria (10 marks)
- [ ] **2 Unit Test** documents in a clear template, covering edge/boundary cases (10 marks)
- [ ] Export as **PDF**

## 🚀 Step 12: Submission
- [ ] Final push to GitHub (check the commit count ≥ 10–15)
- [ ] (Optional) Use one GitHub account for the commits (right now they show as isabella-bella-monteiro, the college-email account)
- [ ] (Optional) Add a README for the portfolio
- [ ] Zip the NetBeans project and submit it on the A&C Moodle page
- [ ] Submit the A&C report on Moodle
- [ ] Submit the SDF PDF on the SDF Moodle page
- [ ] ⏰ Everything submitted before **Sat 21 Nov, 23:59**

## 🎤 Step 13: Q&A Prep (Tue 24 Nov)
- [ ] Review every 🎤 topic above
- [ ] Practise explaining each part of the code out loud
- [ ] Practise explaining at least 1 diagram, 1 user story and 1 unit test
- [ ] Write the student reflection using the difficulties log
