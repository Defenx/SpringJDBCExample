package org.example.constants;

public class SqlQuery {
    public static final String getHumansByNameQuery =
            """
                    SELECT h.id id, h.age, h.name, h.balance, 
                    c.id cars_id, c.cost cars_cost, c.model cars_model, c.human_id  cars_human_id 
                    FROM human h LEFT JOIN car c on h.id = c.human_id 
                    WHERE name = ?;
                    """;

    public static final String getHumansByIdQuery =
            """
                    SELECT h.id id, h.age, h.name, h.balance, 
                    c.id cars_id, c.cost cars_cost, c.model cars_model, c.human_id  cars_human_id 
                    FROM human h LEFT JOIN car c on h.id = c.human_id 
                    WHERE h.id = ?;
                    """;
    public static final String getHumansQuery =
            """
                    SELECT h.id id, h.age, h.name, h.balance, 
                    c.id cars_id, c.cost cars_cost, c.model cars_model, c.human_id  cars_human_id 
                    FROM human h LEFT JOIN car c on h.id = c.human_id;
                    """;
    public static final String countQuery = "SELECT count(*) FROM human;";
    public static final String insertDealQuery = "INSERT INTO deal_history(date, status, buyer_id, seller_id, car_id) VALUES (?,?,?,?,?);";
    public static final String updateCarIdQuery = "UPDATE car SET human_id = ? WHERE id = ?;";
    public static final String findMaxDealHistoryIdQuery = "SELECT MAX(id) FROM deal_history";
    public static final String findMaxHumanIdQuery = "SELECT MAX(id) FROM human";
    public static final String updateDealQuery = "UPDATE deal_history SET date = ?, status = ?, buyer_id = ?, seller_id = ?, car_id = ? where id= ?;";
}
