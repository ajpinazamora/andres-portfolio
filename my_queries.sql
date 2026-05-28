USE urbanfitdb;

-- ########################################################
-- QUERY 1: Basic SELECT / FROM / WHERE
-- ########################################################
-- What this query does:
--   Shows all products that cost more than $100.
--   Demonstrates a simple SELECT/FROM/WHERE query.
--
-- Works? 
--   Yes. Tested after populating the product table.
--
-- Sample output (see screenshot: Q1_products_over_100.png):
--   product_id | name              | brand     | price
--   -----------+-------------------+-----------+--------
--   1          | SkyRide 10K       | FleetFeet | 139.99
--   2          | TempoFly Elite    | Strider   | 179.99
--   3          | CloudStreet Daily | Nimbus    | 119.50
--   4          | TrailStorm Pro    | Summit    | 149.00
--   13         | PaceTrack GPS...  | FleetFeet | 229.99
--   14         | BeatRush Earbuds  | Strider   | 129.99
--
SELECT product_id, name, brand, price
FROM product
WHERE price > 100;


-- ########################################################
-- QUERY 2: JOIN between 2 tables + GROUP BY
-- ########################################################
-- What this query does:
--   Shows each customer and the total number of orders
--   they have placed. Uses a LEFT JOIN so customers with
--   zero orders still appear with total_orders = 0.
--
-- Works?
--   Yes. Returns 15 rows (one for each customer).
--
-- Sample output (see screenshot: Q2_customer_order_counts.png):
--   customer_id | first_name | last_name | total_orders
--   ------------+-----------+-----------+-------------
--   1           | Andres    | Pina      | 1
--   2           | Maria     | Lopez     | 1
--   3           | James     | Carter    | 1
--   ...
--   12          | Julia     | Kim       | 1
--   13          | Mark      | Wilson    | 0
--
SELECT c.customer_id,
       c.first_name,
       c.last_name,
       COUNT(o.order_id) AS total_orders
FROM customer c
LEFT JOIN customerorder o
       ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name;


-- ########################################################
-- QUERY 3: Subquery (products above average price)
-- ########################################################
-- What this query does:
--   Finds all products priced above the AVERAGE product price.
--   Uses a subquery in the WHERE clause.
--
-- Works?
--   Yes. Returns several higher-priced items.
--
-- Sample output (see screenshot: Q3_products_above_avg.png):
--   product_id | name              | price
--   -----------+-------------------+--------
--   1          | SkyRide 10K       | 139.99
--   2          | TempoFly Elite    | 179.99
--   4          | TrailStorm Pro    | 149.00
--   13         | PaceTrack GPS...  | 229.99
--   14         | BeatRush Earbuds  | 129.99
--
SELECT product_id, name, price
FROM product
WHERE price > (SELECT AVG(price) FROM product);


-- ########################################################
-- QUERY 4: Aggregate + GROUP BY (total revenue by status)
-- ########################################################
-- What this query does:
--   Shows total revenue grouped by customerorder status.
--   Uses SUM(total_amount) and GROUP BY.
--
-- Works?
--   Yes. Returns one row per order status.
--
-- Sample output (see screenshot: Q4_revenue_by_status.png):
--   status    | total_revenue
--   ----------+--------------
--   SHIPPED   | 832.41
--   DELIVERED | 796.43
--   PENDING   | 549.96
--   CANCELLED | 69.99
--
SELECT status,
       SUM(total_amount) AS total_revenue
FROM customerorder
GROUP BY status;


-- ########################################################
-- QUERY 5: INSERT a new customer (MODIFICATION)
-- ########################################################
-- What this query does:
--   Inserts a new customer into the customer table.
--
-- Works?
--   Yes. After execution, the new row appears in SELECT * FROM customer.
--
-- Sample check (see screenshot: Q5_insert_customer.png):
--   SELECT * FROM customer WHERE email = 'ana.torres@example.com';
--
INSERT INTO customer (first_name, last_name, email, phone)
VALUES ('Test', 'User', 'test.user@example.com', '817-555-9001');
-- VERIFICATION (ONE screenshot)
SELECT *
FROM customer
WHERE email = 'test.user@example.com';



-- ########################################################
-- QUERY 6: DELETE an address (MODIFICATION)
-- ########################################################
-- What this query does:
--   Deletes an address record with address_id = 22.
--   Demonstrates a DELETE statement.
--   Because shipping_address_id in customerorder uses
--   ON DELETE SET NULL, any order using this address
--   will set its shipping_address_id to NULL.
--
-- Works?
--   Yes. One row is deleted from address (if it exists).
--
-- Sample check (see screenshot: Q6_delete_address.png):
--   BEFORE: SELECT * FROM address WHERE address_id = 22;
--   AFTER:  SELECT * FROM address WHERE address_id = 22;  -- returns 0 rows
--           SELECT * FROM customerorder WHERE shipping_address_id IS NULL;
--
DELETE FROM address
WHERE address_id = 22;
-- VERIFICATION (ONE screenshot)
SELECT *
FROM address
WHERE address_id = 22;


-- ########################################################
-- QUERY 7: Demonstrate ON UPDATE CASCADE (MODIFICATION)
-- ########################################################
-- What this query does:
--   Updates the primary key customer_id from 15 to 150.
--   Because customerorder.customer_id has ON UPDATE CASCADE,
--   all related orders automatically update their customer_id.
--
-- NOTE:
--   Run this ONCE. After running, customer 15 becomes 150.
--
-- Works?
--   Yes. Orders for customer_id 15 now show customer_id 150 instead.
--
-- Sample check (see screenshot: Q7_update_cascade.png):
--   BEFORE: SELECT * FROM customerorder WHERE customer_id = 15;
--   UPDATE: (run the UPDATE below)
--   AFTER:  SELECT * FROM customerorder WHERE customer_id = 150;
--
UPDATE customer
SET customer_id = 150
WHERE customer_id = 15;
-- VERIFICATION (ONE screenshot)
SELECT *
FROM customer
WHERE customer_id IN (15, 150);


-- ########################################################
-- QUERY 8: Demonstrate ON DELETE CASCADE (MODIFICATION)
-- ########################################################
-- What this query does:
--   Deletes product_id = 20 from product.
--   Because inventory.product_id has ON DELETE CASCADE,
--   all inventory records for product_id = 20 are deleted automatically.
--   orderitem.product_id has ON DELETE RESTRICT in this design,
--   so if this DELETE fails, it means orderitem still references 20.
--
-- For a clean demo, you can:
--   a) First check that product 20 exists and is not used in orderitem:
--      SELECT * FROM orderitem WHERE product_id = 20;
--   b) Then run this DELETE.
--
-- Works?
--   Yes, if no orderitems reference product_id = 20.
--
-- Sample check (see screenshot: Q8_delete_cascade.png):
--   BEFORE:
--     SELECT * FROM product   WHERE product_id = 20;
--     SELECT * FROM inventory WHERE product_id = 20;
--   DELETE:
--     (run the DELETE below)
--   AFTER:
--     SELECT * FROM product   WHERE product_id = 20;   -- 0 rows
--     SELECT * FROM inventory WHERE product_id = 20;   -- 0 rows
--
DELETE FROM product
WHERE product_id = 20;
-- VERIFICATION (ONE screenshot)
SELECT *
FROM product
WHERE product_id = 20;
