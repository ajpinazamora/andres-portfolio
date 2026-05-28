"""
front_end.py
Python front-end for the sakila database.

This script connects to the MySQL database and runs the 8 SQL queries.
Each query has its own function, and at least half of the functions use user input().
"""

import mysql.connector

# ==============================
# DB CONNECTION
# ==============================

def get_connection():
    """
    Create and return a connection to the urbanfitdb database.
    """
    conn = mysql.connector.connect(
        host="localhost",
        user="root",
        password="Andy799@",
        database="urbanfitdb"
    )
    return conn


# ==============================
# READ-ONLY QUERIES (1–4)
# ==============================

def query1_products_over_price(conn):
    """
    Query 1 (READ-ONLY + user input):
    Show all products whose price is greater than a user-specified amount.
    """
    try:
        min_price_str = input("Enter minimum price (e.g. 100): ")
        min_price = float(min_price_str)
    except ValueError:
        print("Invalid number, defaulting to 100.")
        min_price = 100.0

    sql = """
        SELECT product_id, name, brand, price
        FROM product
        WHERE price > %s
        ORDER BY price DESC;
    """
    cursor = conn.cursor()
    cursor.execute(sql, (min_price,))
    rows = cursor.fetchall()

    print(f"\nProducts with price > {min_price}:")
    for row in rows:
        print(row)
    print(f"Total rows: {len(rows)}\n")
    cursor.close()


def query2_customer_order_counts(conn):
    """
    Query 2 (READ-ONLY + user input):
    Show each customer and the total number of orders they have placed.
    """
    try:
        min_orders_str = input("Show customers with at least how many orders? (e.g. 0,1,2): ")
        min_orders = int(min_orders_str)
    except ValueError:
        print("Invalid number, defaulting to 0.")
        min_orders = 0

    sql = """
        SELECT c.customer_id,
               c.first_name,
               c.last_name,
               COUNT(o.order_id) AS total_orders
        FROM customer c
        LEFT JOIN customerorder o
               ON c.customer_id = o.customer_id
        GROUP BY c.customer_id, c.first_name, c.last_name
        HAVING total_orders >= %s
        ORDER BY total_orders DESC, c.customer_id;
    """
    cursor = conn.cursor()
    cursor.execute(sql, (min_orders,))
    rows = cursor.fetchall()

    print(f"\nCustomers with at least {min_orders} orders:")
    for row in rows:
        print(row)
    print(f"Total rows: {len(rows)}\n")
    cursor.close()


def query3_products_above_average(conn):
    """
    Query 3 (READ-ONLY):
    Find all products priced above the average product price.
    """
    sql = """
        SELECT product_id, name, price
        FROM product
        WHERE price > (SELECT AVG(price) FROM product)
        ORDER BY price DESC;
    """
    cursor = conn.cursor()
    cursor.execute(sql)
    rows = cursor.fetchall()

    print("\nProducts priced above the average price:")
    for row in rows:
        print(row)
    print(f"Total rows: {len(rows)}\n")
    cursor.close()


def query4_total_revenue_by_status(conn):
    """
    Query 4 (READ-ONLY):
    Show total revenue (sum of total_amount) grouped by order status.
    """
    sql = """
        SELECT status,
               SUM(total_amount) AS total_revenue
        FROM customerorder
        GROUP BY status;
    """
    cursor = conn.cursor()
    cursor.execute(sql)
    rows = cursor.fetchall()

    print("\nTotal revenue grouped by order status:")
    for row in rows:
        print(row)
    print()
    cursor.close()


# ==============================
# MODIFICATION QUERIES (5–8)
# ==============================

def query5_insert_customer(conn):
    """
    Insert a new customer.
    """
    first_name = input("Enter first name: ")
    last_name = input("Enter last name: ")
    email = input("Enter email: ")
    phone = input("Enter phone (optional): ")

    sql = """
        INSERT INTO customer (first_name, last_name, email, phone)
        VALUES (%s, %s, %s, %s);
    """

    cursor = conn.cursor()
    try:
        cursor.execute(sql, (first_name, last_name, email, phone))
        conn.commit()
        print(f"\nInserted customer with ID: {cursor.lastrowid}\n")
    except mysql.connector.Error as err:
        print(f"Error inserting customer: {err}")
        conn.rollback()
    finally:
        cursor.close()


def query6_delete_address(conn):
    """
    Delete an address row by id.
    """
    try:
        addr_id_str = input("Enter address_id to delete: ")
        addr_id = int(addr_id_str)
    except ValueError:
        print("Invalid id; aborting delete.\n")
        return

    sql = "DELETE FROM address WHERE address_id = %s;"
    cursor = conn.cursor()
    try:
        cursor.execute(sql, (addr_id,))
        affected = cursor.rowcount
        conn.commit()
        print(f"\nDeleted {affected} row(s) from address.\n")
    except mysql.connector.Error as err:
        print(f"Error deleting address: {err}")
        conn.rollback()
    finally:
        cursor.close()


def query7_update_customer_id_cascade(conn):
    """
    Demonstrate ON UPDATE CASCADE by changing customer_id.
    """
    try:
        old_id = int(input("Enter existing customer_id to update: "))
        new_id = int(input("Enter new customer_id value: "))
    except ValueError:
        print("Invalid ids; aborting update.\n")
        return

    sql = "UPDATE customer SET customer_id = %s WHERE customer_id = %s;"

    cursor = conn.cursor()
    try:
        cursor.execute(sql, (new_id, old_id))
        affected = cursor.rowcount
        conn.commit()
        print(f"\nUpdated {affected} customer row(s). Cascading applied.\n")
    except mysql.connector.Error as err:
        print(f"Error updating customer_id: {err}")
        conn.rollback()
    finally:
        cursor.close()


def query8_delete_product_cascade_inventory(conn):
    """
    Demonstrate ON DELETE CASCADE by deleting a product.
    """
    try:
        prod_id = int(input("Enter product_id to delete: "))
    except ValueError:
        print("Invalid id; aborting delete.\n")
        return

    sql = "DELETE FROM product WHERE product_id = %s;"

    cursor = conn.cursor()
    try:
        cursor.execute(sql, (prod_id,))
        affected = cursor.rowcount
        conn.commit()
        print(f"\nDeleted {affected} product row(s). Cascading applied.\n")
    except mysql.connector.Error as err:
        print(f"Error deleting product: {err}")
        conn.rollback()
    finally:
        cursor.close()


# ==============================
# MENU / MAIN LOOP
# ==============================

def print_menu():
    print("=====================================")
    print(" Sakila Front-End Menu")
    print("=====================================")
    print("1. Query 1 - Products over a given price")
    print("2. Query 2 - Customer order counts")
    print("3. Query 3 - Products above average price")
    print("4. Query 4 - Total revenue by order status")
    print("5. Query 5 - Insert a new customer")
    print("6. Query 6 - Delete an address")
    print("7. Query 7 - Update customer_id")
    print("8. Query 8 - Delete a product")
    print("0. Exit")
    print("=====================================")


def main():
    try:
        conn = get_connection()
        print("Connected to sakila.\n")
    except mysql.connector.Error as err:
        print(f"Error connecting to database: {err}")
        return

    try:
        while True:
            print_menu()
            choice = input("Choose an option (0-8): ").strip()

            if choice == "1":
                query1_products_over_price(conn)
            elif choice == "2":
                query2_customer_order_counts(conn)
            elif choice == "3":
                query3_products_above_average(conn)
            elif choice == "4":
                query4_total_revenue_by_status(conn)
            elif choice == "5":
                query5_insert_customer(conn)
            elif choice == "6":
                query6_delete_address(conn)
            elif choice == "7":
                query7_update_customer_id_cascade(conn)
            elif choice == "8":
                query8_delete_product_cascade_inventory(conn)
            elif choice == "0":
                print("Exiting program.")
                break
            else:
                print("Invalid choice, please try again.\n")
    finally:
        conn.close()
        print("Connection closed.")


if __name__ == "__main__":
    main()
