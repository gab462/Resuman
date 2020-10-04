-- :name create-users-table
-- :command :execute
-- :result :raw
-- :doc Creates users table
CREATE TABLE users (
  name TEXT NOT NULL,
  email TEXT NOT NULL,
  linkPicture TEXT
);

-- :name get-users :? :*
SELECT ROWID,* FROM users;

-- :name get-user-by-id :? :*
SELECT ROWID,* FROM users
WHERE ROWID = :id;

-- :name insert-user :insert :*
INSERT INTO users (name, email)
VALUES (:name, :email);

-- :name update-user-by-id :! :1
UPDATE users
SET name = :name, email = :email, linkPicture = :linkPicture
WHERE ROWID = :id;

-- :name delete-user-by-id :! :1
DELETE FROM users WHERE ROWID = :id;
