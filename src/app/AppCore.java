package app;

import ErrorHandler.ErrImpl;
import QueryBuilder.QueryBuilder;
import QueryBuilder.compiler.CompilerCompImpl;
import QueryBuilder.validator.ValidatorCompImpl;
import QueryBuilder.QueryBuilderImpl;
import database.Database;
import database.DatabaseImplementation;
import database.MSSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.MainFrame;
import gui.table.TableModel;
import observer.Notification;
import observer.Publisher;
import observer.Subscriber;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import resource.implementation.InformationResource;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private QueryBuilder queryBuilder;
    private ErrHandler errHandler;

    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MSSQLrepository(this.settings));
        this.queryBuilder = new QueryBuilderImpl(new ValidatorCompImpl(), new CompilerCompImpl());
        this.errHandler = new ErrImpl();
        errHandler.addErrorSub(MainFrame.getInstance());
        tableModel = new TableModel();
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }


    public void loadResource(){
        InformationResource ir = (InformationResource) this.database.loadResource();
        this.notifySubscribers(new Notification(NotificationCode.RESOURCE_LOADED,ir));
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        //this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }


    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }


    public Database getDatabase() {
        return database;
    }

    public Settings getSettings() {
        return settings;
    }

    public QueryBuilder getQueryBuilder(){
        return queryBuilder;
    }

    public ErrHandler getErrHandler() {
        return errHandler;
    }
}
