package view.form;

import controller.FlightController;
import controller.PassengerController;
import controller.PlaneController;
import model.flight.FlightModel;
import model.flight.data.Flight;
import model.flight.data.Seat;
import model.flight.list_model.FlightListModel;
import model.flight.list_model.SeatListModel;
import model.passenger.PassengerModel;
import model.passenger.data.Passenger;
import model.passenger.data.PassengerType;
import model.passenger.list_model.PassengerListModel;
import model.plane.PlaneModel;
import model.plane.data.Plane;
import model.plane.list_model.PlaneListModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AirportManagementSystemsGUI extends JFrame {
    private JButton updateUserButton;
    private JPanel panel1;
    private JButton addUserButton;
    private JButton removeUserButton;
    private JTextField textFieldLuggageCount;
    private JTextField textFieldYearOfBirth;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JComboBox<PassengerType> comboBoxPassengerType;
    private JScrollPane scrollPaneAllPassengers;
    private JTextField textFieldDeparture;
    private JTextField textFieldLanding;
    private JTextField textFieldDepartureDate;
    private JTextField textFieldReturnDate;
    private JButton addFlightButton;
    private JButton removeFlightButton;
    private JButton cancelTicketButton;
    private JButton buyTicketButton;
    private JTextArea textAreaPassengerId;
    private JScrollPane scrollPaneFlights;
    private JScrollPane scrollPaneSeats;
    private JTextField textFieldTicketPrice;
    private JButton addPlaneButton;
    private JTextField textFieldAddPlane;
    private JSpinner spinnerCapacity;
    private JButton removePlaneButton;
    private JTextField textFieldRemovePlane;
    private JScrollPane scrollPanePlanes;
    private PassengerModel passengerModel;
    private FlightModel flightModel;
    private PlaneModel planeModel;
    private int indexOfSelectedPassenger;
    private int indexOfSelectedFlight;
    private int indexOfSelectedSeat;
    private int indexOfSelectedPlane;

    private final int[] selectedSeat = {-1};

    private JList<String> seatList = new JList<>();

    public AirportManagementSystemsGUI() {
        passengerModel = new PassengerModel();
        flightModel = new FlightModel();
        planeModel = new PlaneModel();

        initFrame();
        setupPassengerManagementUI();
        setupFlightManagementUI();
        setupPlaneManagementUI();
    }

    private void initFrame() {
        add(panel1);
        setSize(1000, 400);
        setTitle("Sabiha Gökçen");
    }

    private void setupPassengerManagementUI() {
        scrollPaneAllPassengers.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));

        comboBoxPassengerType.addItem(PassengerType.Business);
        comboBoxPassengerType.addItem(PassengerType.Economy);
        comboBoxPassengerType.addItem(PassengerType.Family);

        PassengerController passengerController = new PassengerController(passengerModel);

        refreshPassengerPane();

        addUserButton.addActionListener(e -> {
            passengerController.addUser(
                    textFieldName.getText(),
                    textFieldSurname.getText(),
                    textFieldLuggageCount.getText(),
                    textFieldYearOfBirth.getText(),
                    (PassengerType) comboBoxPassengerType.getSelectedItem()
            );
            refreshPassengerPane();
            clearPassengerTextFields();
        });

        removeUserButton.addActionListener(e -> {
            Passenger selectedPassenger=  passengerModel.getPassenger(indexOfSelectedPassenger);
            passengerController.removeUser(selectedPassenger.getId());
            refreshPassengerPane();
            clearPassengerTextFields();
        });

        updateUserButton.addActionListener(e -> {
        Passenger selectedPassenger = passengerModel.getPassenger(indexOfSelectedPassenger);
        passengerController.updateUser(
                textFieldName.getText(),
                textFieldSurname.getText(),
                textFieldLuggageCount.getText(),
                textFieldYearOfBirth.getText(),
                (PassengerType) comboBoxPassengerType.getSelectedItem(),
                selectedPassenger.getId()
        );
        refreshPassengerPane();
        clearPassengerTextFields();
    });
}

    private void clearPassengerTextFields() {
        textFieldName.setText("");
        textFieldSurname.setText("");
        textFieldYearOfBirth.setText("");
        textFieldLuggageCount.setText("");
    }

    private void refreshPassengerPane() {
        JList<String> passengerList = setupAllPassengersPane();
        scrollPaneAllPassengers.setViewportView(passengerList);
    }

    private JList<String> setupAllPassengersPane() {
        JList<String> passengerList = new JList<>();
        PassengerListModel listModel = new PassengerListModel(passengerModel.getAllPassengers());
        passengerList.setModel(listModel);
        passengerList.getSelectionModel().addListSelectionListener(e -> {
            indexOfSelectedPassenger = passengerList.getSelectedIndex();
            Passenger selectedPassenger = passengerModel.getPassenger(indexOfSelectedPassenger);

            textFieldName.setText(selectedPassenger.getName());
            textFieldSurname.setText(selectedPassenger.getSurname());
            textFieldLuggageCount.setText(Integer.toString(selectedPassenger.getLuggageCount()));
            textFieldYearOfBirth.setText(Integer.toString(selectedPassenger.getYearOfBirth()));
            comboBoxPassengerType.setSelectedItem(selectedPassenger.getPassengerType());
        });
        return passengerList;
    }

    private void setupFlightManagementUI() {
        FlightController flightController = new FlightController(passengerModel, flightModel);

        scrollPaneFlights.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPaneSeats.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));

        refreshFlightPane();

        addFlightButton.addActionListener(e -> {
            flightController.addFlight(textFieldDeparture.getText(), textFieldLanding.getText(), textFieldDepartureDate.getText(), textFieldReturnDate.getText());
            refreshFlightPane();
            clearFlightTextFields();
        });

        removeFlightButton.addActionListener(e -> {
            Flight selectedFlight = flightModel.getFlight(indexOfSelectedFlight);
            flightController.removeFlight(selectedFlight.getId());
            refreshFlightPane();
            clearFlightTextFields();
        });

        buyTicketButton.addActionListener(e -> {
            if (selectedSeat[0] == -1) {
                new ErrorDialog("No seat is chosen");
            } else {
                String passengerIndex = textAreaPassengerId.getText();

                Flight selectedFlight = flightModel.getFlight(indexOfSelectedFlight);
                List<Seat> seats = flightModel.getSeatsByFlightId(selectedFlight.getId());

                flightController.buyTicket(seats.get(selectedSeat[0]), passengerIndex);
                setupManageTicketsPane(selectedFlight.getId());
                textAreaPassengerId.setText("");
                textFieldTicketPrice.setText("");
            }
        });

        cancelTicketButton.addActionListener(e -> {
            Flight selectedFlight = flightModel.getFlight(indexOfSelectedFlight);
            List<Seat> seats = flightModel.getSeatsByFlightId(selectedFlight.getId());

            flightController.cancelTicket(seats.get(selectedSeat[0]).getId());
            setupManageTicketsPane(selectedFlight.getId());
        });
    }

    private void clearFlightTextFields() {
        textFieldDeparture.setText("");
        textFieldDepartureDate.setText("");
        textFieldLanding.setText("");
        textFieldReturnDate.setText("");
    }

    private void refreshFlightPane() {
        JList<String> flightList = setupCurrentFlightPane();
        scrollPaneFlights.setViewportView(flightList);
    }

    private JList<String> setupCurrentFlightPane() {
        JList<String> flightList = new JList<>();
        FlightListModel listModel = new FlightListModel(flightModel.getAllFlights());
        flightList.setModel(listModel);
        flightList.getSelectionModel().addListSelectionListener(e -> {
            indexOfSelectedFlight = flightList.getSelectedIndex();
            Flight selectedFlight = flightModel.getFlight(indexOfSelectedFlight);

            textFieldDeparture.setText(selectedFlight.getDepartureLocation());
            textFieldLanding.setText(selectedFlight.getLandingLocation());
            textFieldDepartureDate.setText(selectedFlight.getDepartureDate());
            textFieldReturnDate.setText("");

            setupManageTicketsPane(selectedFlight.getId());
        });
        return flightList;
    }

    private void setupManageTicketsPane(int flightId) {
        List<Seat> seats = flightModel.getSeatsByFlightId(flightId);
        SeatListModel seatListModel = new SeatListModel(seats);
        seatList.setModel(seatListModel);
        scrollPaneSeats.setViewportView(seatList);

        seatList.getSelectionModel().addListSelectionListener(event -> {
            indexOfSelectedSeat = seatList.getSelectedIndex();
            selectedSeat[0] = indexOfSelectedSeat;

            textFieldTicketPrice.setText(String.valueOf(seats.get(selectedSeat[0]).getPrice()));
        });
    }

    private void setupPlaneManagementUI() {
        scrollPanePlanes.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));

        PlaneController planeController = new PlaneController(planeModel);

        refreshPlanePane();

        addPlaneButton.addActionListener(e -> {
            planeController.addPlane(textFieldAddPlane.getText(), (Integer) spinnerCapacity.getValue());
            refreshPlanePane();
            textFieldAddPlane.setText("");
            spinnerCapacity.setValue(0);
        });

        removePlaneButton.addActionListener(e -> {
            Plane selectedPlane = planeModel.getPlane(indexOfSelectedPlane);
            planeController.removePlane(selectedPlane.getId());
            refreshPlanePane();
            textFieldRemovePlane.setText("");
        });
    }

    private void refreshPlanePane() {
        JList<String> planeList = setupAllPlanesPane();
        scrollPanePlanes.setViewportView(planeList);
    }

    private JList<String> setupAllPlanesPane() {
        JList<String> planeList = new JList<>();
        PlaneListModel listModel = new PlaneListModel(planeModel.getAllPlanes());
        planeList.setModel(listModel);
        planeList.getSelectionModel().addListSelectionListener(e -> {
            indexOfSelectedPlane = planeList.getSelectedIndex();
            Plane selectedPlane = planeModel.getPlane(indexOfSelectedPlane);
            textFieldRemovePlane.setText(selectedPlane.getName());
            spinnerCapacity.setValue(selectedPlane.getCapacity());
        });
        return planeList;
    }
}