
# 📚 Library Management System  

A Java Servlet-based web application for managing a library.  
It supports **users**, **librarians**, and **admins** with different roles and functionalities.  

---

## 🚀 Features  

### 🔹 Guest & Authentication  
- **Signup / Register** – Create a new account.  
- **Login** – Redirects users based on their role (student, librarian, admin).  
- **Guest Access** – Limited access without registration (view specific books).  
- **Logout** – Ends session securely.  

### 🔹 User Functions  
- View available books.  
- Search books by ISBN.  
- See active borrowings.  
- Request a book borrowing.  
- Return borrowed books.  
- Write reviews for books.  

### 🔹 Librarian Functions  
- Accept or reject borrow requests.  
- Update user data.  
- Manage books (add, update, change availability).  
- View active borrowings.  
- Manage borrow/return requests.  

### 🔹 Admin Functions  
- View all users.  
- Delete users by username.  
- Access system statistics.  

---

## 🛠️ Servlets Implemented  

1. **AcceptBorrowing** – Approve borrowing & update availability.  
2. **ChangeAvailability** – Mark a book as available/unavailable.  
3. **CreateBook** – Add a new book.  
4. **DeleteUser** – Remove a user by username.  
5. **GetBooks** – Retrieve available books.  
6. **GetData** – Fetch user info (user/librarian).  
7. **GetStatistics** – Retrieve system statistics.  
8. **GetUsers** – List all users.  
9. **Login** – Handle user login (GET/POST).  
10. **Login_Admin** – Handle admin login.  
11. **Logout** – End user session.  
12. **Register (MyServlet)** – Register a new user.  
13. **ParateRequest** – Librarian gets borrowing requests.  
14. **RequestData** – Manage borrow requests & expiring deadlines.  
15. **ReturnTheBook** – Confirm book return.  
16. **SeeActiveBorrowing** – Show all active borrowings.  
17. **SeeBorrowing** – Retrieve borrowing history.  
18. **SendBook** – Handle book return by user.  
19. **UpdateData** – Update user data.  
20. **WriteReview** – User adds a review for a book.  
21. **FindBooks** – Search for books.  

---

## 💻 Tech Stack  

- **Backend:** Java Servlets  
- **Frontend:** HTML, Bootstrap, minimal CSS, AJAX  
- **Database:** SQL-based (manual queries, no ORM)  

---

## 📦 Installation  

1. Clone the repository:  
   ```bash
   git clone <your-repo-link>
   cd library-management
