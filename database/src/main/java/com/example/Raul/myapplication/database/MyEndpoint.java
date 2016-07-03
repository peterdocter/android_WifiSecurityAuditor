/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Raul.myapplication.database;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "database.myapplication.Raul.example.com",
    ownerName = "database.myapplication.Raul.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /*
    @ApiMethod(name = "storeRouter")
    public void storeRouter(Router router) {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        try {
            Key routerParentKey = KeyFactory.createKey("RouterParent", "todo.txt");
            Entity routerEntity = new Entity("Router", router.getId(), routerParentKey);
            routerEntity.setProperty("bssid", router.getBssid());
            datastoreService.put(routerEntity);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    @ApiMethod(name = "getRouters")
    public List<Router> getRouters() {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Key routerParentKey = KeyFactory.createKey("RouterParent", "todo.txt");
        Query query = new Query(routerParentKey);
        List<Entity> results = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());
        ArrayList<Router> routers = new ArrayList<Router>();
        for (Entity result : results) {
            Router router = new Router();
            router.setId(result.getKey().getId());
            router.setBssid((String) result.getProperty("bssid"));
            routers.add(router);
        }

        return routers;
    }

    @ApiMethod(name = "clearRouters")
    public void clearTasks() {
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastoreService.beginTransaction();
        try {
            Key routerParentKey = KeyFactory.createKey("RouterParent", "todo.txt");
            Query query = new Query(routerParentKey);
            List<Entity> results = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());
            for (Entity result : results) {
                datastoreService.delete(result.getKey());
            }
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }
    */
}
