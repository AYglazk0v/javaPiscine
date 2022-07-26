INSERT INTO users (login, password)
VALUES ('Djuradj', '123456');
INSERT INTO users (login, password)
VALUES ('Madina', '123456');
INSERT INTO users (login, password)
VALUES ('Alfredas', '123456');
INSERT INTO users (login, password)
VALUES ('Roman', '123456');
INSERT INTO users (login, password)
VALUES ('Alexus', '123456');

INSERT INTO chatroom (name, owner)
VALUES ('Work', 4);
INSERT INTO chatroom (name, owner)
VALUES ('Shop', 3);
INSERT INTO chatroom (name, owner)
VALUES ('Neighbourhood', 1);
INSERT INTO chatroom (name, owner)
VALUES ('Sport', 1);
INSERT INTO chatroom (name, owner)
VALUES ('Family', 5);

INSERT INTO message (author, room, text, date)
VALUES (1, 2, 'About turn! March!', TO_TIMESTAMP('2030-010-21 10:32:24', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (2, 1, 'Away with a talk-show.', TO_TIMESTAMP('2023-010-9 8:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (3, 2, 'Silence, you speakers!', TO_TIMESTAMP('2022-07-25 10:16:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (4, 1, 'Comrade mauser, you have the floor.', TO_TIMESTAMP('2024-01-1 10:30:20', 'YYYY-MM-DD HH:MI:SS'));
INSERT INTO message (author, room, text, date)
VALUES (1, 3, 'Down with the law which for us Adam and Eve have left.', TO_TIMESTAMP('2024-12-30 11:30:20', 'YYYY-MM-DD HH:MI:SS'));