package en.fluffyBerserk.gui.screens;

import en.fluffyBerserk.Main;
import en.fluffyBerserk.form.RegisterForm;
import en.fluffyBerserk.gui.utils.AttachCSS;
import en.fluffyBerserk.persistence.InsertTask;
import en.fluffyBerserk.persistence.SelectTask;
import en.fluffyBerserk.persistence.models.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.TypedQuery;
import java.security.SecureRandom;

public final class RegisterScreen extends BaseScreen {

    private final RegisterForm form = new RegisterForm();
    private VBox root;
    private Scene scene;


    @Override
    protected Scene buildScene() {

        init();

        setUpUsernameField();
        setUpPasswordField();
        setUpConfirmPasswordField();

        final Button registerButton = setUpRegisterButton();
        final Button backButton = setUpBackButton();

        root.getChildren().addAll(registerButton, backButton);

        // Attach enter key to submit form
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                registerButton.fire();
            }
        });

        return scene;
    }

    @NotNull
    private Button setUpBackButton() {
        final Button backButton = new Button("Back");
        backButton.setOnAction(event -> Main.app.changeScreen(new HomeScreen()));
        backButton.getStyleClass().add("back-button");
        return backButton;
    }

    @NotNull
    private Button setUpRegisterButton() {
        final Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            form.clearErrors();

            if (!form.validate()) {
                Main.app.redrawScene();
                return;
            }

            final User existingUser = new SelectTask<User>().singleNamedQuery(manager -> {
                TypedQuery<User> query = manager.createNamedQuery("User.byUsername", User.class);
                query.setParameter(1, form.getUsername());
                return query;
            });

            if (existingUser != null) {
                form.addError("username", "This username is already used!");
                Main.app.redrawScene();
                return;
            }

            final PasswordEncoder encoder = new BCryptPasswordEncoder(16, new SecureRandom());

            User user = new User();
            user.setUsername(form.getUsername());
            user.setPassword(encoder.encode(form.getPassword()));
            user.setIsAdmin((byte) 0);

            user = new InsertTask<User>().insert(user);

            Main.app.login(user);
            Main.app.changeScreen(new SaveSlotsScreen());
        });
        return registerButton;
    }

    private void setUpConfirmPasswordField() {
        final PasswordField passwordConfirmField = new PasswordField();
        passwordConfirmField.setText(form.getPasswordConfirm());
        passwordConfirmField.setPromptText("Enter password confirmation");
        passwordConfirmField.textProperty().addListener((observable, oldValue, newValue) -> {
            form.setPasswordConfirm(newValue);
        });

        root.getChildren().add(new Label("Password confirmation"));
        root.getChildren().add(passwordConfirmField);

        // Render errors if any
        for (String error : form.getErrorsForField("passwordConfirm")) {
            final Text errorText = new Text(error);
            errorText.setFill(Color.RED);
            root.getChildren().add(errorText);
        }
    }

    private void setUpPasswordField() {
        final PasswordField passwordField = new PasswordField();
        passwordField.setText(form.getPassword());
        passwordField.setPromptText("Enter password");
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            form.setPassword(newValue);
        });

        root.getChildren().add(new Label("Password"));
        root.getChildren().add(passwordField);

        // Render errors if any
        for (String error : form.getErrorsForField("password")) {
            final Text errorText = new Text(error);
            errorText.setFill(Color.RED);
            root.getChildren().add(errorText);
        }
    }

    private void setUpUsernameField() {
        final TextField usernameField = new TextField(form.getUsername());
        usernameField.setPromptText("Enter username");
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            form.setUsername(newValue);
        });

        root.getChildren().add(new Label("Username"));
        root.getChildren().add(usernameField);

        // Render errors if any
        for (String error : form.getErrorsForField("username")) {
            final Text errorText = new Text(error);
            errorText.setFill(Color.RED);
            root.getChildren().add(errorText);
        }
    }

    private void init() {
        root = new VBox();
        root.getStyleClass().addAll("vbox", "log-in");

        scene = new Scene(root);
        AttachCSS.attachCSS(scene);
    }

    @Override
    public void onEnter() {
        if (Main.app.isUserLoggedIn()) {
            throw new RuntimeException("Logged in user cannot go to register screen!");
        }
    }
}
