-- :name create-users-table
-- :command :execute
-- :result :raw
-- :doc Creates users table
CREATE TABLE users (
  name TEXT NOT NULL,
  username TEXT NOT NULL,
  email TEXT NOT NULL,
  password TEXT NOT NULL,
  linkPicture TEXT
);

-- :name get-users :? :*
SELECT rowid,name,username,email,linkPicture FROM users;

-- :name get-user-by-id :? :*
SELECT rowid,name,username,email,linkPicture FROM users
WHERE rowid = :id;

-- :name get-user-by-username :? :*
SELECT rowid,username,password FROM users
WHERE username = :username;

-- :name insert-user :insert :*
INSERT INTO users (name, username, email, password)
VALUES (:name, :username, :email, :password); SELECT last_insert_rowid();

-- :name update-user-by-id :! :1
UPDATE users
SET name = :name, username = :username, email = :email, password = :password, linkPicture = :linkPicture
WHERE rowid = :id;

-- :name delete-user-by-id :! :1
DELETE FROM users WHERE rowid = :id;

-- :name create-projects-table
-- :command :execute
-- :result :raw
-- :doc Creates projects table
CREATE TABLE projects (
  title TEXT NOT NULL,
  description TEXT NOT NULL,
  source TEXT NOT NULL,
  info TEXT,
  thumbnail TEXT,
  user INTEGER NOT NULL
);

-- :name get-projects :? :*
SELECT rowid,* FROM projects;

-- :name get-project-by-id :? :*
SELECT rowid,* FROM projects
WHERE rowid = :id;

-- :name get-projects-by-user :? :*
SELECT rowid,* FROM projects
WHERE user = :id;

-- :name insert-project :insert :*
INSERT INTO projects (title, description, source, info, thumbnail, user)
VALUES (:title, :description, :source, :info, :thumbnail, :user); SELECT last_insert_rowid();

-- :name update-project-by-id :! :1
UPDATE projects
SET title = :title, description = :description, source = :source, info = :info, thumbnail = :thumbnail, user = :user
WHERE rowid = :id;

-- :name delete-project-by-id :! :1
DELETE FROM projects WHERE rowid = :id;
