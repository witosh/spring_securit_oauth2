-- pass: github_client/github_secret
INSERT INTO oauth_client_details 
	(client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity) VALUES 
	('github_client', '{bcrypt}$2a$10$WqcMPO5ENWSvDjC5jVe0TeRdsET/0uJf6i7JvriYOYtU/ibZKXkxG', 'GITHUB', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

-- pass: witosh/test
INSERT INTO users 
	(id, username, password, enabled) VALUES 
		(1, 'witosh', '{bcrypt}$2a$10$yk.31eiE8o8mWf/A6z70LeFXb0FslQpb4C/suYgCJIzD1gc0UBDJu', 1);

INSERT INTO authorities 
	(username, authority) VALUES 
		('witosh', 'ROLE_USER');