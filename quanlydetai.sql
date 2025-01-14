CREATE DATABASE IF NOT EXISTS UniversityResearchManagement;

USE UniversityResearchManagement;

-- Bảng Quản lý Khoa
CREATE TABLE Faculties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
) ENGINE=InnoDB;

-- Bảng Quản lý Đề tài
CREATE TABLE Projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    status ENUM('Pending', 'In Progress', 'Completed', 'Cancelled') DEFAULT 'Pending',
    faculty_id INT,
    FOREIGN KEY (faculty_id) REFERENCES Faculties(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- Bảng Quản lý Người dùng
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role ENUM('Admin', 'Lecturer', 'Student') NOT NULL
) ENGINE=InnoDB;

-- Bảng Thành viên Đề tài
CREATE TABLE ProjectMembers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    user_id INT NOT NULL,
    role ENUM('Leader', 'Member', 'Supporter') DEFAULT 'Member',
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Bảng Hội đồng Đánh giá
CREATE TABLE EvaluationBoards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
) ENGINE=InnoDB;

-- Bảng Đánh giá
CREATE TABLE Evaluations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    board_id INT NOT NULL,
    evaluation_date DATE,
    comments TEXT,
    score DECIMAL(5,2),
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES EvaluationBoards(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Bảng Tài liệu
CREATE TABLE Documents (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES Projects(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Bảng Báo cáo
CREATE TABLE Reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    report_type ENUM('Annual', 'Progress', 'Custom') NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
) ENGINE=InnoDB;
