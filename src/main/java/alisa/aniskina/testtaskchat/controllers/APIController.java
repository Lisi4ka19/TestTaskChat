package alisa.aniskina.testtaskchat.controllers;

import Enums.Status;
import alisa.aniskina.testtaskchat.entity.Message;
import alisa.aniskina.testtaskchat.entity.Product;
import alisa.aniskina.testtaskchat.entity.StatusMessage;
import alisa.aniskina.testtaskchat.operations.SimpleOperations;
import alisa.aniskina.testtaskchat.service.MessageService;
import alisa.aniskina.testtaskchat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/")
public class APIController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ProductService productService;

    private final int USER_ID_MANAGER = 0;

    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    public List<Message> getAllMessages(@RequestParam ("senderId") String sender_Id, @RequestParam ("recipientId") String recipient_Id ,@RequestParam(value = "dateBegin", required = false) String date1, @RequestParam(value = "dateEnd", required = false) String date2){

        Date dateBegin = SimpleOperations.convertStringToDate(date1);
        Date dateEnd = SimpleOperations.convertStringToDate(date2);
        Integer senderId = SimpleOperations.convertStringToInt(sender_Id);
        Integer recipientId = SimpleOperations.convertStringToInt(recipient_Id);


        List <Message> allMessages = messageService.getAllMessages(senderId, recipientId, dateBegin, dateEnd);
        return allMessages;
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message){

        message.setDate(new Date());
        message.setStatusId("0");

        messageService.saveMessage(message);
        return message;
    }

    @GetMapping("/newMessages") List<Message> getNewMessage(@RequestParam ("senderId") String sender_Id, @RequestParam ("recipientId") String recipient_Id){

        Integer senderId = SimpleOperations.convertStringToInt(sender_Id);
        Integer recipientId = SimpleOperations.convertStringToInt(recipient_Id);


        List<Message> listNewMessage = messageService.getNewMessage(senderId, recipientId);

        return listNewMessage;
    }

    @GetMapping("/sendMisProducts")
    public Message sendMissingProducts(@RequestParam ("client_Id") String client_Id){

        Integer clientId = SimpleOperations.convertStringToInt(client_Id);

        String text = "";
        List<Product> productList = productService.getAvailableProduct(productService.getProductList());

        for (Product product:productList) {
            text+= "Товар \"" + product.getName() + "\" отсутствует \n";
        }

        Message message = new Message(USER_ID_MANAGER, clientId, text, new Date(), "0");

        messageService.saveMessage(message);

        return message;
    }

    @GetMapping("/sendProducts")
    public Message sendProducts(@RequestParam ("client_Id") String client_Id){

        Integer clientId = SimpleOperations.convertStringToInt(client_Id);

        String text = "";
        List<Product> productList = productService.getProductList();

        for (Product product:productList) {
            text+= "Товар: \"" + product.getName() + " \n";
        }

        Message message = new Message(USER_ID_MANAGER, clientId, text, new Date(), "0");

        messageService.saveMessage(message);

        return message;
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        List<Product>productList = productService.getProductList();

        return productList;
    }

    @GetMapping("changeAvailable")
    public StatusMessage changeAvailableProduct(@RequestParam ("user_Id") String user_Id, @RequestParam ("product_Id") String product_Id){
        Integer userId = SimpleOperations.convertStringToInt(user_Id);

        String ErrorText;
        if(userId!=USER_ID_MANAGER){
            ErrorText = "Пользователь с id - " + user_Id + " не может изменять доступность товара";
            StatusMessage statusMessage = new StatusMessage(Status.ERROR, ErrorText);
            return statusMessage;
        }

        Integer productId = SimpleOperations.convertStringToInt(product_Id);
        ErrorText = productService.changeAvailableProduct(productId, false);


        StatusMessage statusMessage = new StatusMessage(ErrorText.length()==0?Status.OK:Status.ERROR, ErrorText);

        return statusMessage;
    }


}
