package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Initializer implements ServletContextListener {

    public static boolean dbActive = true;

    public boolean isDbActive() {
        return dbActive;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //Suppliers
        Supplier wannahavesomedrugs = new Supplier ("Wanna have some drugs? Corp", "Supported by Daniel Radcliffe");
        supplierDataStore.add(wannahavesomedrugs);
        Supplier olliwandermagicwands = new Supplier("Olliwander's Magic Wands", "Very best wands in town, where the size doesn't matter");
        supplierDataStore.add(olliwandermagicwands);
        Supplier weasleystutoring = new Supplier("Weasley's Tutoring", "Most effective spells, from experts");
        supplierDataStore.add(weasleystutoring);
        Supplier cheapslaves = new Supplier("Cheap Slaves LTD", "From beasts to humans, for weddings, stags, birthdays, funerals, etc.");
        supplierDataStore.add(cheapslaves);

        //Product categories
        ProductCategory magictool = new ProductCategory("Magic tool", "Tools","Utilities for magic");
        productCategoryDataStore.add(magictool);
        ProductCategory magicspell = new ProductCategory("Magic spell", "Spells", "Most effective words against enemies");
        productCategoryDataStore.add(magicspell);
        ProductCategory rental = new ProductCategory("Rental", "Rentals", "Various species around the world");
        productCategoryDataStore.add(rental);
        ProductCategory adultsonly = new ProductCategory("18+", "Adults only", "BEWARE! Adults only! :O");
        productCategoryDataStore.add(adultsonly);

        //Products
        Product magicdust = new Product("Magic Dust", 10, "USD", "Pure happiness and joy", magictool, wannahavesomedrugs);
        productDataStore.add(magicdust);
        Product cloak = new Product("Cloak of invisibility", 50, "USD", "Watch out!", magictool, wannahavesomedrugs);
        productDataStore.add(cloak);
        Product wandphoenix = new Product ("Magic wand - phoenix feather", 20, "USD", "Optimal for fiery wizards and witches", magictool, olliwandermagicwands);
        productDataStore.add(wandphoenix);
        Product wanddragon = new Product ("Magic wand - dragon heart string", 20, "USD", "Optimal for fiery wizards and witches", magictool, olliwandermagicwands);
        productDataStore.add(wanddragon);
        Product wandunicorn = new Product ("Magic wand - unicorn hair", 20, "USD", "Optimal for fiery wizards and witches", magictool, olliwandermagicwands);
        productDataStore.add(wandunicorn);
        Product invito = new Product("Invito", 30, "USD", "Don't be so distant - pull your loved ones closer", magicspell, weasleystutoring);
        productDataStore.add(invito);
        Product expecto = new Product("Expecto Patronum(with custom animal type)", 30, "USD", "Good defender against dementors", magicspell, weasleystutoring  );
        productDataStore.add(expecto);
        Product animagus = new Product ("To be an animagus(with custom animal type", 40, "USD", "Why just wonder wildlife if you can be a part of it?", magicspell, weasleystutoring);
        productDataStore.add(animagus);
        Product leviosa = new Product ("Vingardium leviosa", 30, "USD", "Not leviosaaa!", magicspell, weasleystutoring);
        productDataStore.add(leviosa);
        Product hungarian = new Product ("Hungarian Horntail", 200, "USD", "Coolest dragon ever", rental, cheapslaves);
        productDataStore.add(hungarian);
        Product hippogriff = new Product ("Hippogriff", 200, "USD", "Known flying object", rental, cheapslaves);
        productDataStore.add(hippogriff);
        Product phoenix = new Product ("Phoenix", 200, "USD", "Pet for life", rental, cheapslaves);
        productDataStore.add(phoenix);
        Product hagrid = new Product ("Rubeus Hagrid", 300, "USD", "'Happi birthdae, Hary!'", rental, cheapslaves);
        productDataStore.add(hagrid);
        Product random = new Product ("Random human pack", 50, "USD", "Surpriiiise!", rental, cheapslaves);
        productDataStore.add(random);
        Product tear = new Product("Phoenix tear", 600, "USD", "Wounds are for the weak", adultsonly, wannahavesomedrugs);
        productDataStore.add(tear);
        Product mushroom= new Product("Magic mushrooms", 600, "USD", "Original magic", adultsonly, wannahavesomedrugs);
        productDataStore.add(mushroom);
        Product root = new Product("Mandragora root", 600, "USD", "Solution for blue screen of death ", adultsonly, wannahavesomedrugs);
        productDataStore.add(root);
        Product fang = new Product("Basilisc fang", 600, "USD", "If you're bored of the book...", adultsonly, wannahavesomedrugs);
        productDataStore.add(fang);
        Product blood = new Product("Unicorn blood", 600, "USD", "Stay alive - drink blood!", adultsonly, wannahavesomedrugs);
        productDataStore.add(blood);

    }
}
