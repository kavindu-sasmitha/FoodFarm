package edu.lk.ijse.farm.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartTm {
   private String itemId;
   private String itemName;
   private int CartQty;
   private double unitPrice;
   private double total;
   private Button btnRemove;
}
