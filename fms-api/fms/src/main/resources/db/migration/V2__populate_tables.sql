INSERT INTO users (id, username, password, email)
VALUES
  ('c7d3d8c2-83ce-4d11-937a-0ae3c5f7c8b1', 'john_doe', 'hashed_password1', 'john.doe@example.com'),
  ('f5a35e27-2fe2-4a88-8e5b-e7b481c0e7b2', 'jane_smith', 'hashed_password2', 'jane.smith@example.com'),
  ('b0b964ec-0b22-4fcf-bc44-eb51e7796c3c', 'user3', 'hashed_password3', 'user3@example.com'),
  ('9e7f7d9d-5c05-493d-92b6-51ee4066d4b1', 'user4', 'hashed_password4', 'user4@example.com'),
  ('27de0e9c-44df-482e-a2b5-f657f3b6c28a', 'user5', 'hashed_password5', 'user5@example.com');

 INSERT INTO files (id, name, path, type, user_id)
 VALUES
    ('d69f16e7-4a90-4ee6-b29b-628f0c789d0a', 'file1.txt', '/files/user1/file1.txt', 'txt', 'c7d3d8c2-83ce-4d11-937a-0ae3c5f7c8b1'),
    ('ad7d7e6f-22d9-4fa7-a4c6-9acfe9597461', 'file2.jpg', '/files/user1/file2.jpg', 'image/jpeg', 'c7d3d8c2-83ce-4d11-937a-0ae3c5f7c8b1');

    -- User 2 Files
    INSERT INTO files (id, name, path, type, user_id)
    VALUES
      ('dcd6efbd-1bf7-44e3-bde3-d3a6f982af3b', 'file3.txt', '/files/user2/file3.txt', 'text/plain', 'f5a35e27-2fe2-4a88-8e5b-e7b481c0e7b2'),
      ('ef3e1a53-c2e9-43ea-a1a3-63299d0ad6c5', 'file4.jpg', '/files/user2/file4.jpg', 'image/jpeg', 'f5a35e27-2fe2-4a88-8e5b-e7b481c0e7b2'),
      ('e8c4779c-6b09-49e0-9b58-39962e9a7c50', 'file5.pdf', '/files/user2/file5.pdf', 'application/pdf', 'f5a35e27-2fe2-4a88-8e5b-e7b481c0e7b2');

    -- User 3 Files
    INSERT INTO files (id, name, path, type, user_id)
    VALUES
      ('dd15c2c0-611c-459b-9433-8455e33c9610', 'file6.txt', '/files/user3/file6.txt', 'text/plain', 'b0b964ec-0b22-4fcf-bc44-eb51e7796c3c'),
      ('7c18d6b2-6a70-4de9-917a-6364e4a994b8', 'file7.jpg', '/files/user3/file7.jpg', 'image/jpeg', 'b0b964ec-0b22-4fcf-bc44-eb51e7796c3c');

    -- User 4 Files
    INSERT INTO files (id, name, path, type, user_id)
    VALUES
      ('b6ce6c41-176f-4b3b-b1f6-28a10bf8a592', 'file8.txt', '/files/user4/file8.txt', 'text/plain', '9e7f7d9d-5c05-493d-92b6-51ee4066d4b1'),
      ('aa57f7e2-94a6-46da-af17-66a74a086617', 'file9.jpg', '/files/user4/file9.jpg', 'image/jpeg', '9e7f7d9d-5c05-493d-92b6-51ee4066d4b1'),
      ('e12ef0de-c740-428e-9c2d-08da79a4c0fc', 'file10.pdf', '/files/user4/file10.pdf', 'application/pdf', '9e7f7d9d-5c05-493d-92b6-51ee4066d4b1');

    -- User 5 Files
    INSERT INTO files (id, name, path, type, user_id)
    VALUES
      ('4719b773-9d59-4a4f-8312-e424a9a5db38', 'file11.txt', '/files/user5/file11.txt', 'text/plain', '27de0e9c-44df-482e-a2b5-f657f3b6c28a'),
      ('c914d8ef-3cbf-44dd-b045-b3c3d58b217c', 'file12.jpg', '/files/user5/file12.jpg', 'image/jpeg', '27de0e9c-44df-482e-a2b5-f657f3b6c28a');
