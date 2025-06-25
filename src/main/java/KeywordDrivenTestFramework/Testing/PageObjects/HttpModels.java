package KeywordDrivenTestFramework.Testing.PageObjects;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

public class HttpModels {

    public static class RetakeCollectionPhoto_PostRequest {
        public String description;
        public List<EventHistory> eventHistory;
        public String OrganisationId;
        public String resourceType;
        public String status;
        public String storeId;
        public StoreTaskData storeTaskData;
        public String storeTaskId;
        public String storeTaskType;
        public String title;
    }

    public static class Task {
        public String storeId;
        public String storeTaskId;
        public String storeTaskType;
        public StoreTaskData storeTaskData;
        public String title;
        public String description;
    }


    public static class EventHistory {
        public String clientVersion;
        public String dateTime;
        public String deviceIdentifier;
        public EventData eventData;
        public String eventType;
        public Location location;
        public String newStatus;
        public String oldStatus;
        public String searchId;
        public String searchTime;
        public String searchType;
        public String staffId;
    }

    public static class EventData {
        public String preadvicedId;
        public List<Packages> packages;
    }

    public static class Events {
        public String eventType;
        public String dateTime;
    }

    public static class Packages {
        public String addedDateTime;
        public boolean dlPackageDocumentMissing;
        public String dlPackageId;
        public String labelValue;
    }

    public static class Location {
        public Store store;
        public String storeId;
        public String shelfId;
        public Load load;
        public Hub hub;
    }

    public static class StoreTaskData {
        public List<String> items;
    }

    public static class Store {
        public String doddleStoreId;
    }

    public static class Hub {
        public String hubId;
    }

    public static class GetDocument_Response {
        public List<Resources> resources;
    }


    public static class Resources {
        public Alerts alerts;
        public boolean completed;
        public Cost cost;
        public Customer customer;
        public String dlPackageId;
        public List<EventHistory> eventHistory;
        public IdChecks idChecks;
        public boolean isDemo;
        public String itemId;
        public String labelValue;
        public Location location;
        public String organisationId;
        public Payment payment;
        public boolean preadvisedDelivery;
        public String referenceId;
        public String resourceType;
        public String retailerId;
        public String retailerOrderId;
        public boolean signatureRequired;
        public String status;
        public String collectionDeadlineDateTime;
        public List<Events> events;
        public boolean preadvisedLate;
        public String expectedDate;


    }

    public static class updateCollection {
        public Resource resource;
        public List<Events> events;
    }

    public static class Resource {
        public String collectionDeadlineDateTime;
    }

    public static class CreateCollection {
        public String organisationId;
        public String labelValue;
        public boolean preadvisedLate;
        public Location location;
        public String retailerId;
        public String retailerOrderId;
        public String expectedDate;
        public List<Integer> dimensions;
        public int weight;
        public String status;
        public String collectionDeadlineDateTime;
        public List<EventHistory> eventHistory;
    }

    public static class BootStrap {
        public AlertMessages alertMessages;
    }

    public static class AlertMessages {
        public List<General> general;
    }

    public static class General {
        public String messageText;
        public String messageId;
    }

    public static class retailerDoc {
        public String resourceType;
        public String retailerId;
        public boolean signatureRequired;
        public boolean isAgentEligible;
        public String organisationId;
        public String retailerName;
        public String collectionDeadlineDays;
        public Returns returns;
    }

    public static class Alerts {
        public boolean requiresPhotoId;
        public boolean requiresAdditionalIdCheck;
    }

    public static class Cost {
        public int basePriceGross;
        public String currency;
        public int finalPriceGross;
        public int vatRate;
    }

    public static class Customer {
        public String email;
        public Contact contact;
        public Name name;
    }

    public static class Name {
        public String firstName;
        public String lastName;
    }

    public static class Contact {
        public String emailAddress;
        public String phoneNumber;
    }

    public static class IdChecks {
        public String requiredType;
    }

    public static class Payment {
        public String currency;
        public int oustandingGross;
        //Stub value below. Needs update.
        public List<String> payments;
    }

    public static class client_credentials_Reponse {
        public String access_token;
        public String token_type;
        public String expires_in;
        public String scope;
        public User user;
        public AppInfo appinfo;
    }

    public static class User {
        //Stub. Needs update.
    }

    public static class AppInfo {
        public String appName;
        public String organisationId;
    }

    public static class Returns {
        public CustomerReturns customerReturns;
    }

    public static class CustomerReturns {
        public boolean prebookedOnly;
        public Accepted accepted;
    }

    public static class Accepted {
        @SerializedName("default")
        public boolean _default;
    }

    public static class GetDocument_DLPackage {
        public List<DLResources> resources;
    }

    public static class DLResources {
        public String bookingType;
        public String coreItemId;
        public Destination destination;
        public String dlPackageId;
        public List<EventHistory> eventHistory;
        public boolean isTemporaryLabel;
        public String labelValue;
        public Location location;
        public Load load;
        public String retailer;
        public String store;
        public String vehicle;
        public Origin origin;
        public String packageType;
        public String resourceType;
        public String status;
        public String transferStrategy;
        public List<Waypoints> waypoints;
    }

    public static class Origin {
        public String doddleStoreId;
        public String hubId;
    }

    public static class Destination {
        public String retailerId;
        public String retailerRoutingId;
    }

    public static class Load {
        public String containerId;
        public String coreLoadId;
        public String dlLoadId;
    }

    public static class Waypoints {
        public boolean completed;
        public String despatchCarrierId;
        public String hubId;
        public String type;
    }

    public static class GetDlLoad_Response {
        public List<DlLoadResource> resources;
    }

    public static class DlLoadResource {
        public String bookingType;
        public String containerId;
        public String dlLoadId;
        public Destination destination;
        public List<EventHistory> eventHistory;
        public From from;
        public boolean isMixed;
        public String loadType;
        public Location location;
        public String logisticsServiceId;
        public Origin origin;
        public String resourceType;
        public String status;
        public List<Packages> packages;
        public Parcel parcel;
        public String transferStrategy;
        public List<Waypoints> waypoints;

    }

    public static class From {
        public String hubId;
    }

    public static class Parcel {
        public String descriptionOfGoods;
    }

    public static class preadviseParcel {
        public String retailerID;
        public String orderID;
        public Customer customer;
        public List<Parcels> parcels;
    }

    public static class Parcels {
        public String id;
        public String storeID;
    }
}
