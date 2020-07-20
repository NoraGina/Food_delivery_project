package view;

import model.*;
import repository.*;

import java.util.HashSet;
import java.util.Set;

public class ConsoleMain {

    static UserDao userDao = new UserHibernate();
    static RestaurantDao restaurantDao = new RestaurantHibernate();
    static ProductDao productDao = new ProductHibernate();
    static CustomerDao customerDao = new CustomerHibernate();

    public static void main(String[] args) {

        saveUser();
        displayInfoUser();

        Long userId = 2L;
        User userToUpdate =selectUserById(userId);

         Restaurant restaurant = createFirstRestaurant(userToUpdate);
        createProductsForFirstRestaurant(restaurant, userToUpdate);
        displayInfoRestaurant();
        displayInfoProducts();

        createCustomer();
        selectAllCustomer();
        System.out.println(selectWhereConditionIdCustomer(5L));
        System.out.println("\nSearch customer by id");
        System.out.println("_______________________________");
        final Long idCustomerToSearch = 5L;
        final Customer customerToSearch = selectWhereConditionIdCustomer(idCustomerToSearch);

        System.out.println("\nUpdate phone for costumer");
        System.out.println("_______________________________");
        final String updateCustomerPhone = "0721422555";
        updatePhoneCustomerSearched(customerToSearch,updateCustomerPhone);
        customerToSearch.displayInfoCustomer();
    }

    
    //Display info customer
    private static void selectAllCustomer(){
        System.out.println("____________________________________");
        for (Customer customer : customerDao.readAllCustomer()) {
            customer.displayInfoCustomer();
        }
        System.out.println("-------------------------------------");
    }

    //Select customer by Id
    private static Customer selectWhereConditionIdCustomer(final Long id){
        return customerDao.findCustomerById(id);
    }

    //Update customer
    private static void updatePhoneCustomerSearched(Customer customerToUpdate, String newPhone){
        customerToUpdate.setPhone(newPhone);
        customerDao.updateCustomer(customerToUpdate);
    }

    //Delete customer
    private static void deleteCustomer(Customer customerToDelete){
        customerDao.deleteCustomer(customerToDelete.getId());
    }

    //Create customers
    private static void createCustomer(){
        Customer firstCustomer = new Customer();
        firstCustomer.setFirstName("Ion");
        firstCustomer.setLastName("Agarbiceanu");
        firstCustomer.setPassword("1234");
        firstCustomer.setAddress("Oradea, Soarelui, 4");
        firstCustomer.setPhone("0721455283");
        firstCustomer.setEmail("agarbiceanu@gmail");
        customerDao.createCustomer(firstCustomer);

        Customer secondCustomer = new Customer();
        secondCustomer.setFirstName("Veronica");
        secondCustomer.setLastName("Micle");
        secondCustomer.setPassword("veronica");
        secondCustomer.setAddress("Oradea, Caisilor, 14");
        secondCustomer.setPhone("0259455836");
        secondCustomer.setEmail("micle@gmail");
        customerDao.createCustomer(secondCustomer);

        Customer thirdCustomer = new Customer();
        thirdCustomer.setFirstName("Mihai");
        thirdCustomer.setLastName("Eminescu");
        thirdCustomer.setPassword("eminescu");
        thirdCustomer.setAddress("Oradea, Visinilor, 8");
        thirdCustomer.setPhone("0745288324");
        thirdCustomer.setEmail("eminescu@gmail");
        customerDao.createCustomer(thirdCustomer);
    }

    //Find user by First name and Last name
    private static User findUserByFirstAndLastName(String firstName, String lastname){
        return userDao.findUserByFirstNameAndLastName(firstName, lastname);
    }

    private static Restaurant selectRestaurantById(Long id){
        return restaurantDao.findRestaurantById(id);
    }


    //Display info restaurant
    private static void displayInfoRestaurant(){
        System.out.println("---------------------------");
        for(Restaurant restaurant :restaurantDao.readAllRestaurants()){
            restaurant.displayInfoRestaurant();
        }
        System.out.println("-------------------------------");
    }

    //Display info products
    private static void displayInfoProducts(){
        System.out.println("---------------------------");
        for(Product product : productDao.readAllProducts()){
            product.displayInfoProduct();
        }
    }

    //Create products for first restaurant
    private static Set<Product> createProductsForFirstRestaurant(Restaurant firstRestaurant, User user) {
        Set<Product> productInfos = new HashSet<>();
        Product productMargerita = new Product();
        productMargerita.setProductName("Pizza Margeritta");
        productMargerita.setDescription("pizza dough, mozzarella, tomatoes, oregano.300gr");
        productMargerita.setPrice(10.0);
        productMargerita.setRestaurant(firstRestaurant);
        productMargerita.setUser(user);
        productInfos.add(productMargerita);

        Product productBolognese = new Product();
        productBolognese.setProductName("Spaghetti Bolognese");
        productBolognese.setDescription("spaghetti, meat, tomatoes, parmesan, basil.300gr");
        productBolognese.setPrice(11.0);
        productBolognese.setRestaurant(firstRestaurant);
        productBolognese.setUser(user);
        productInfos.add(productBolognese);

        Product productQuatroFromagio = new Product();
        productQuatroFromagio.setProductName("Pizza Quatro Fromagio");
        productQuatroFromagio.setDescription("pizza dough, mozzarella, tomatoes, cheddar, gorgonzola, parmesan, oregano.300gr");
        productQuatroFromagio.setPrice(13.5);
        productQuatroFromagio.setRestaurant(firstRestaurant);
        productQuatroFromagio.setUser(user);
        productInfos.add(productQuatroFromagio);

        Product productCarbonara = new Product();
        productCarbonara.setProductName("Spaghetti carbonara");
        productCarbonara.setDescription("spaghetti, meat, tomatoes, egs, parmesan, basil.300gr");
        productCarbonara.setPrice(11.0);
        productCarbonara.setRestaurant(firstRestaurant);
        productCarbonara.setUser(user);
        productInfos.add(productCarbonara);

        return productInfos;
    }
    //Create First Restaurant
    private static Restaurant createFirstRestaurant(User user) {
        Restaurant firstRestaurant = new Restaurant();
        firstRestaurant.setRestaurantName("Party Fowl");
        firstRestaurant.setRestaurantPhone("0745288455");
        firstRestaurant.setRestaurantEmail("partyfowl@gmail");
        firstRestaurant.setProductSet(createProductsForFirstRestaurant(firstRestaurant, user));
        firstRestaurant.setUser(user);
        restaurantDao.createRestaurant(firstRestaurant);
        return firstRestaurant;

    }

    //Select user by id
    private static User selectUserById(Long id){
        return userDao.findUserById(id);
    }

    //CREATE USER
    private static void saveUser(){
        User user = new User();
        user.setFirstName("George ");
        user.setLastName("Crisan");
        user.setDepartment(Department.ADMINISTRATOR);
        user.setPhone("0721654789");
        user.setEmail("crisan.gmail");
        user.setUserPassword("1234");
        userDao.createUser(user);

        User user1 = new User();
        user1.setFirstName("Vlad");
        user1.setLastName("Mihalcea");
        user1.setUserPassword("1234");
        user1.setPhone("0721321456");
        user1.setDepartment(Department.EMPLOYEE);
        user1.setEmail("mihalcea.gmail");
        userDao.createUser(user1);


    }

    //Display info user
    private static void displayInfoUser(){
        System.out.println("---------------------");
        for(User user : userDao.readAllUsers()){
            user.displayInfoUser();
        }
        System.out.println("---------------------------");
    }

}
