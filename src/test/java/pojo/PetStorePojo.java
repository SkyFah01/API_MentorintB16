package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
  @Setter
  @Getter
public class PetStorePojo {


      private int id;
      private String name;
      private List<String> photoUrls;
      private List<Object> tags;
      private String status;
      private PetStoreCategoryPojo category;//call from another Pojo class that we create separate id and name of category

}
