//package za.ac.student_trade.controller;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//import za.ac.student_trade.domain.*;
//import za.ac.student_trade.factory.*;
//import za.ac.student_trade.service.Impl.TransactionServiceImpl;
//import za.ac.student_trade.service.Impl.StudentServiceImpl;
//import za.ac.student_trade.service.Impl.ProductServiceImpl;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class TransactionControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private TransactionServiceImpl transactionService;
//
//    @Autowired
//    private StudentServiceImpl studentService;
//
//    @Autowired
//    private ProductServiceImpl productService;
//
//    private MockMvc mockMvc;
//
//    private Transaction transaction;
//    private Product product;
//    private Student buyer, seller;
//
//    @BeforeAll
//    void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//
//        // --- Seller ---
//        Address sellerAddress = AddressFactory.createAddress("10", "Seller Street", "Seller Suburb", "Cape Town", "Western Cape", 7001);
//        Residence sellerResidence = ResidenceFactory.createResidence("Seller Residence", "S101", 1, "Block S", sellerAddress);
//        seller = StudentFactory.createStudent("Alice", "Seller", "seller@gmail.com", "pass123", sellerResidence);
//        seller = studentService.create(seller); // Persist seller
//
//        // --- Buyer ---
//        Address buyerAddress = AddressFactory.createAddress("20", "Buyer Street", "Buyer Suburb", "Cape Town", "Western Cape", 7002);
//        Residence buyerResidence = ResidenceFactory.createResidence("Buyer Residence", "B102", 2, "Block B", buyerAddress);
//        buyer = StudentFactory.createStudent("Bob", "Buyer", "buyer@gmail.com", "pass456", buyerResidence);
//        buyer = studentService.create(buyer); // Persist buyer
//
//        // --- Product ---
//        product = ProductFactory.create(
//                "P001",
//                "Gaming Laptop",
//                "High-performance gaming laptop with RTX graphics",
//                2500.00,
//                "Electronics",
//                true,
//                "Like New",
//                "Cape Town",
//                new byte[0], // placeholder
//                LocalDate.now(),
//                seller
//        );
//        product = productService.create(product); // Persist product
//
//        // --- Transaction ---
//        transaction = TransactionFactory.createTransaction(
//                LocalDateTime.now(),
//                new byte[0], // placeholder
//                "Gaming Laptop",
//                "High-performance gaming laptop with RTX graphics",
//                "Like New",
//                2500.00,
//                product,
//                buyer
//        );
//        transaction = transactionService.create(transaction); // Persist transaction
//    }
//
//    @Test
//    @Order(1)
//    void testCreateTransaction() throws Exception {
//        mockMvc.perform(post("/api/transaction/create")
//                        .param("productId", String.valueOf(product.getProductId()))
//                        .param("buyerId", buyer.getStudentId()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @Order(2)
//    void testGetTransaction() throws Exception {
//        mockMvc.perform(get("/api/transaction/read/{id}", transaction.getTransactionId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productLabel").value(transaction.getProductLabel()))
//                .andExpect(jsonPath("$.productDescription").value(transaction.getProductDescription()))
//                .andExpect(jsonPath("$.productCondition").value(transaction.getProductCondition()));
//    }
//
//    @Test
//    @Order(3)
//    void testUpdateTransaction() throws Exception {
//        // Update the productLabel
//        transaction = new Transaction.Builder()
//                .copy(transaction)
//                .setProductLabel("Excellent")
//                .build();
//
//        transactionService.update(transaction); // Persist the update
//
//        mockMvc.perform(get("/api/transaction/read/{id}", transaction.getTransactionId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productLabel").value("Excellent"));
//    }
//
//    @Test
//    @Order(4)
//    void testGetAllTransactions() throws Exception {
//        mockMvc.perform(get("/api/transaction/read/{id}", transaction.getTransactionId()))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    @Order(5)
////    void testDeleteTransaction() throws Exception {
////        transactionService.delete(transaction.getTransactionId());
////
////        mockMvc.perform(get("/api/transaction/read/{id}", transaction.getTransactionId()))
////                .andExpect(status().isNotFound());
////    }
//}
