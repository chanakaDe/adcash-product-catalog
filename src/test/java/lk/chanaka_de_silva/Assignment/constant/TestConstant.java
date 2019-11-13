/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.constant;

/**
 *
 * @author chanaka
 */
public class TestConstant {

    public static final String HOST = "http://localhost";
    public static final int PORT = 8080;

    public static final String USER_NAME = "chanaka";
    public static final String PASSWORD = "123";

    public static final String CATEGORY_END_POINT = getRootUrl() + "/api/assignment/categories";
    public static final String PRODUCT_END_POINT = getRootUrl() + "/api/assignment/products";

    public static String getRootUrl() {
        return HOST + ":" + PORT;
    }

}
