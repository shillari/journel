-- Check if table 'category' exists
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'category') THEN
        -- If table doesn't exist, create it
        CREATE TABLE category (
            id INT PRIMARY KEY,
            category_name VARCHAR(255) NOT NULL
        );
    END IF;
END $$;

-- Insert data
INSERT INTO category (id, category_name) VALUES
(1, 'Work'),
(2, 'Personal'),
(3, 'Health'),
(4, 'Education'),
(5, 'Finance'),
(6, 'Other');