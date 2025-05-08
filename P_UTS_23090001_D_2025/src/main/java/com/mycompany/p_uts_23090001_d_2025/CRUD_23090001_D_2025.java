/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_uts_23090001_d_2025;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.Bson;
/**
 *
 * @author ASUS
 */
public class CRUD_23090001_D_2025 {

    public static void main(String[] args) {

        String url = "mongodb://localhost:27017/";
        MongoClient client = MongoClients.create(url);
        MongoDatabase db = client.getDatabase("uts_23090001_D_2025");
        MongoCollection<Document> coll = db.getCollection("coll_23090001_D_2025");

        // CREATE
        Document doc1 = new Document("nama", "Delia")
                .append("umur", 21);
        Document doc2 = new Document("nama", "Rangga")
                .append("alamat", "Slawi");
        Document doc3 = new Document("nama", "Ayu")
                .append("prodi", "Informatika")
                .append("angkatan", 2023);
        
        
        coll.insertMany(Arrays.asList(doc1, doc2, doc3));
        System.out.println("=== Data Berhasil Ditambahkan ===\n");

        // READ
        System.out.println("=== Data Saat Ini ===");
        FindIterable<Document> data = coll.find();
        for (Document d : data) {
            System.out.println(d.toJson());
        }

        // UPDATE 
        Bson filterUpdate = Filters.eq("nama", "Delia");
        Bson updateField = Updates.set("umur", 22);
        coll.updateOne(filterUpdate, updateField);
        System.out.println("\n=== Data Setelah Update (Delia) ===");
        data = coll.find();
        for (Document d : data) {
            System.out.println(d.toJson());
        }

        // DELETE 
        Bson deleteFilter = Filters.eq("nama", "Rangga");
        coll.deleteOne(deleteFilter);
        System.out.println("\n=== Data Setelah Delete (Rangga) ===");
        data = coll.find();
        for (Document d : data) {
            System.out.println(d.toJson());
        }

        // SEARCHING
        String keyword = "Ayu";
        Bson searchFilter = Filters.regex("nama", ".*" + keyword + ".*", "i");
        Document hasil = coll.find(searchFilter).first();
        System.out.println("\n=== Hasil Pencarian (keyword: " + keyword + ") ===");
        if (hasil != null) {
            System.out.println(hasil.toJson());
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }
}


