import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;

import java.util.*;

public class MongoDBDataStoreUtilities {
    static DBCollection myReviews;

    public static DBCollection getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("RiderReviews");
        myReviews = db.getCollection("reviews");
        return myReviews;
    }


    public static String insertReview(String userName,String orderId, String driverName, String rate,String date, String text) {
        try {
            getConnection();
            BasicDBObject doc = new BasicDBObject("title", "myReviews").
                    append("userName",userName).
                    append("orderId", orderId).
                    append("driverName", driverName).
                    append("rate", rate).
                    append("date",date).
                    append("text", text);
            myReviews.insert(doc);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    public static HashMap<String, ArrayList<Review>> selectReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = myReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("userName"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("userName"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("userName"));
                Review review = new Review(obj.getString("userName"), obj.getString("orderId"), obj.getString("driverName"), obj.getString("rate"), obj.getString("date"), obj.getString("text"));
                //add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }
    }
        public static HashMap<String, ArrayList<Review>> selectRate(){
            HashMap<String, ArrayList<Review>> rates = null;

            try {

                getConnection();
                DBCursor cursor = myReviews.find();
                rates = new HashMap<String, ArrayList<Review>>();
                while (cursor.hasNext()) {
                    BasicDBObject obj = (BasicDBObject) cursor.next();

                    if (!rates.containsKey(obj.getString("driverName"))) {
                        ArrayList<Review> arr = new ArrayList<Review>();
                        rates.put(obj.getString("driverName"), arr);
                    }
                    ArrayList<Review> listReview = rates.get(obj.getString("driverName"));
                    Review review1 = new Review(obj.getString("driverName"), obj.getString("rate"));
                    //add to review hashmap
                    listReview.add(review1);

                }
                return rates;
            } catch (Exception e) {
                rates = null;
                return rates;
            }
    }

    }
