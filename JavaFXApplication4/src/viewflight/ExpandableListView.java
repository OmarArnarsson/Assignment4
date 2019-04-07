/**
 * Copyright (C) 2015 uphy.jp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package viewflight;

import controllerflight.BookingManager;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelflight.ConnectedFlight;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Yuhi Ishikura
 */
public class ExpandableListView<E> extends ListView<E> {

    private ContentProvider<E> contentProvider = new ContentProvider<E>() {
        @Override
        public String getTitleOf(final E item) {
            return item.toString();
        }

        @Override
        public String getContentOf(final E item) {
            return getTitleOf(item);
        }

        @Override
        public ConnectedFlight getFlights(final E item) {
            return new ConnectedFlight();
        }
    };

    private final Set<E> expandedItems = new HashSet<E>();

    public ExpandableListView() {
        setSelectionModel(null);
        setCellFactory(new Callback<ListView<E>, ListCell<E>>() {
            @Override
            public ListCell<E> call(final ListView<E> param) {
                final TitledPane titledPane = new TitledPane();
                final Text contentArea = new Text();
                final Button button = new Button();

                titledPane.setAnimated(false);
                titledPane.setCollapsible(true);
                titledPane.setExpanded(false);

                button.setText("Panta");
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        // Ekkert hér
                    }
                });

                final BorderPane contentAreaWrapper = new BorderPane();
                contentAreaWrapper.setLeft(contentArea);
                contentAreaWrapper.setRight(button);
                titledPane.setContent(contentAreaWrapper);

                titledPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) {
                        final boolean expanded = titledPane.isExpanded();
                        final E item = (E)titledPane.getUserData();
                        if (item == null) {
                            return;
                        }
                        if (expanded) {
                            expandedItems.add(item);
                        } else {
                            expandedItems.remove(item);
                        }
                    }
                });
                return new ListCell<E>() {
                    @Override
                    protected void updateItem(final E item, final boolean empty) {
                        super.updateItem(item, empty);


                        button.setText("Panta");
                        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Stage stage = new Stage();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("PassengerView.fxml"));
                                BookingManager bookingManager = new BookingManager();
                                bookingManager.setFlights(contentProvider.getFlights(item));

                                PassengerViewController passangerViewController = new PassengerViewController();
                                passangerViewController.setBooker(bookingManager);
                                passangerViewController.setStage(stage);

                                loader.setController(passangerViewController);

                                Parent root = null;
                                try {
                                    root = loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setTitle("Passanger information");
                                stage.show();
                            }
                        });

                        final BorderPane contentAreaWrapper = new BorderPane();
                        contentAreaWrapper.setLeft(contentArea);
                        contentAreaWrapper.setRight(button);
                        titledPane.setContent(contentAreaWrapper);

                        titledPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(final MouseEvent event) {
                                final boolean expanded = titledPane.isExpanded();
                                final E item = (E)titledPane.getUserData();
                                if (item == null) {
                                    return;
                                }
                                if (expanded) {
                                    expandedItems.add(item);
                                } else {
                                    expandedItems.remove(item);
                                }
                            }
                        });


                        if (empty) {
                            titledPane.setText("");
                            contentArea.setText("");
                            return;
                        }
                        final boolean expanded = isExpanded(item);
                        titledPane.setUserData(item);
                        titledPane.setExpanded(expanded);
                        // Hér get ég sett innihald titledPane
                        titledPane.setText(contentProvider.getTitleOf(item));

                        // Hér get ég sett innihald contentArea
                        contentArea.setText(contentProvider.getContentOf(item));
                        setGraphic(titledPane);
                    }
                };
            }
        });
        getStylesheets().add(String.format("/%s/style.css", getClass().getPackage().getName().replaceAll("\\.", "/")));
    }

    public void setContentProvider(final ContentProvider<E> contentProvider) {
        this.contentProvider = contentProvider;
    }

    public void expand(E item) {
        expand(item, true);
    }

    public void collapse(E item) {
        expand(item, false);
    }

    private void expand(E item, boolean expand) {
        if (expand) {
            this.expandedItems.add(item);
        } else {
            this.expandedItems.remove(item);
        }

        ObservableList<E> o = getItems();
        setItems(null);
        setItems(o);
    }

    public boolean isExpanded(E item) {
        return this.expandedItems.contains(item);
    }

    public static interface ContentProvider<E> {

        String getTitleOf(E item);

        String getContentOf(E item);

        ConnectedFlight getFlights(E item);

    }

}