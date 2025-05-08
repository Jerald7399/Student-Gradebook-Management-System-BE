# 📘 Student Gradebook Management System

## 📝 1. Overview

The **Student Gradebook Management System** is a full-stack web application that enables teachers to manage and track the academic performance of students. It supports secure registration/login and provides features for adding students, managing subjects, assigning grades, and generating report cards.

---

## 🎯 2. Goals & Objectives

- Provide a secure platform for teachers to log in and manage their classrooms.
- Allow CRUD operations on students, subjects, and grades.
- Generate student-wise report cards with statistics.
- Export report cards as PDF.

---

## 👥 3. Target Users

- **Teachers**: Primary users who manage students and grades.

---

## ⚙️ 4. Functional Requirements

### 4.1 🔐 Authentication

- Teacher registration with email & password.
- JWT-based login and logout.
- Password encryption and secure session handling.

### 4.2 🎓 Student Management

- Add new student (Name, Roll No, Class, DOB).
- View, update, or delete student details.

### 4.3 📚 Subject Management

- Add, view, edit, and delete subjects.
- Subjects are teacher-specific.

### 4.4 🏆 Grade Management

- Assign grades/marks to students per subject.
- Edit or delete grades.
- Show average, highest, and lowest grades.

### 4.5 🧾 Report Card Generation

- View report card per student.
- Display subject-wise grades with totals and comments.
- Export report card to PDF.

---

## 📈 5. Non-Functional Requirements

- Responsive UI (Mobile & Desktop)
- Fast loading performance
- RESTful APIs using JSON
- Clean and intuitive user experience

---

## 🛠️ 6. Tech Stack

| Layer        | Technology                       |
|-------------|-----------------------------------|
| Frontend     | React.js, Tailwind CSS            |
| Backend      | Spring Boot, Spring Security, JWT |
| Database     | PostgreSQL                        |
| API Comm     | Axios                             |
| Auth         | JWT Token, BCrypt                 |
| Deployment   | Docker, Vercel/Heroku             |

---

## 🔌 7. API Endpoints (Sample)

### 🔐 Authentication

- `POST /api/auth/register` – Register teacher  
- `POST /api/auth/login` – Login

### 🎓 Students

- `GET /api/students`  
- `POST /api/students`  
- `PUT /api/students/{id}`  
- `DELETE /api/students/{id}`

### 📚 Subjects

- `GET /api/subjects`  
- `POST /api/subjects`  
- `DELETE /api/subjects/{id}`

### 🏆 Grades

- `POST /api/grades`  
- `GET /api/grades/student/{id}`  
- `PUT /api/grades/{id}`  
- `DELETE /api/grades/{id}`

### 🧾 Reports

- `GET /api/report/{studentId}`  
- `GET /api/report/{studentId}/export` – Export as PDF

---

## 🧬 8. Database Schema (Simplified)

### 👨‍🏫 Teachers

- `id` (PK)  
- `name`  
- `email`  
- `password`

### 🎓 Students

- `id` (PK)  
- `name`  
- `roll_no`  
- `class`  
- `dob`  
- `teacher_id` (FK)

### 📚 Subjects

- `id` (PK)  
- `name`  
- `teacher_id` (FK)

### 🏆 Grades

- `id` (PK)  
- `student_id` (FK)  
- `subject_id` (FK)  
- `score`  
- `remarks`

---

## 💻 9. UI Pages

1. Login Page  
2. Register Page  
3. Dashboard (Overview)  
4. Student Management  
5. Subject Management  
6. Grade Management  
7. Report Card Viewer  
8. PDF Export View

---

## ✅ 10. Success Criteria

- Teachers can securely manage students, subjects, and grades.
- Report cards display correct information.
- PDFs are accurate and well-formatted.
- UI is responsive and smooth.

---
