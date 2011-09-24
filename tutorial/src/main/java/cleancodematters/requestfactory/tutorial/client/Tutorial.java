package cleancodematters.requestfactory.tutorial.client;

import java.util.Arrays;

import cleancodematters.requestfactory.tutorial.client.PizzaRequestFactory.PizzaRequestContext;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tutorial implements EntryPoint {
  
  private Button sendButton;
  private TextBox nameField;
  private Label errorLabel;
  private TextBox ingredientField;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    sendButton = new Button( "Order pizza" );
    nameField = new TextBox();
    nameField.setText( "Pizza Name" );
    ingredientField = new TextBox();
    ingredientField.setText( "Ingredient" );
    errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick( ClickEvent event ) {
        orderPizza();
      }
    });

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("nameFieldContainer").add(ingredientField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
  }

  private void orderPizza() {
    errorLabel.setText("");
    sendButton.setEnabled(false);
    
    // Then, we send the input to the server.
    PizzaRequestContext context = createFactory().context();
    PizzaProxy pizza = context.create( PizzaProxy.class );
    pizza.setName( nameField.getText() );
    IngredientProxy ingredient = context.create( IngredientProxy.class );
    ingredient.setName( ingredientField.getText() );
    pizza.setIngredients( Arrays.asList( ingredient ) );
    
    context.save( pizza ).fire( new Receiver<Void>() {
      @Override
      public void onSuccess( Void arg0 ) {
        createConfirmationDialogBox().center();
        sendButton.setEnabled(true);
      }
      @Override
      public void onFailure( ServerFailure error ) {
        errorLabel.setText( error.getMessage() );
        sendButton.setEnabled(true);
      }
    });
    
  }  
  private static DialogBox createConfirmationDialogBox() {
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setModal( true );
    dialogBox.setText("Request Factory Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Thanks for your order</b>"));
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });
    return dialogBox;
  }

  private static PizzaRequestFactory createFactory() {
    PizzaRequestFactory factory = GWT.create( PizzaRequestFactory.class );
    factory.initialize( new SimpleEventBus() );
    return factory;
  }
  
}
