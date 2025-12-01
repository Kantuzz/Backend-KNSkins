package com.csgoskins.catalogservice;

import com.csgoskins.catalogservice.model.Categoria;
import com.csgoskins.catalogservice.model.Producto;
import com.csgoskins.catalogservice.model.Usuario;
import com.csgoskins.catalogservice.repository.CategoriaRepository;
import com.csgoskins.catalogservice.repository.ProductoRepository;
import com.csgoskins.catalogservice.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initData(
            CategoriaRepository categoriaRepo,
            ProductoRepository productoRepo,
            UsuarioRepository usuarioRepo
    ) {
        return args -> {

            // =======================================================
            // CREAR USUARIO ADMIN (solo si no existe)
            // =======================================================
            if (!usuarioRepo.existsByEmail("admin@knskins.gg")) {

                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setEmail("admin@knskins.gg");
                admin.setPassword(sha256("Admin1!"));   // HASH obligatorio
                admin.setRole("ADMIN");

                usuarioRepo.save(admin);

                System.out.println(">>> ADMIN creado: admin@knskins.gg / Admin1!");
            }

            // =======================================================
            // SI YA EXISTEN CATEGORÍAS, NO CARGAR PRODUCTOS DE NUEVO
            // =======================================================
            if (categoriaRepo.count() > 0) {
                System.out.println(">>> Categorías existentes. No se cargan productos.");
                return;
            }

            // =======================================================
            // CATEGORÍAS
            // =======================================================
            Categoria rifles = categoriaRepo.save(Categoria.builder().nombre("Rifles").build());
            Categoria pistolas = categoriaRepo.save(Categoria.builder().nombre("Pistolas").build());
            Categoria cuchillos = categoriaRepo.save(Categoria.builder().nombre("Cuchillos").build());
            Categoria guantes = categoriaRepo.save(Categoria.builder().nombre("Guantes").build());

            // =======================================================
            // RIFLES
            // =======================================================
            productoRepo.save(Producto.builder()
                    .nombre("AK-47 Redline")
                    .precio(30000)
                    .stock(10)
                    .sku("AKRED01")
                    .destacado(false)
                    .imagenUrl("/img/skins/AK-REDLINE.png")
                    .categoria(rifles)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("AK-47 Bloodsport")
                    .precio(45000)
                    .stock(7)
                    .sku("AKBLD01")
                    .destacado(true)
                    .imagenUrl("/img/skins/AK-BLOODSPORT.png")
                    .categoria(rifles)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("AK-47 Vulcan")
                    .precio(55000)
                    .stock(5)
                    .sku("AKVUL01")
                    .destacado(false)
                    .imagenUrl("/img/skins/AK-VULCAN.png")
                    .categoria(rifles)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("M4A4 Desolate")
                    .precio(90000)
                    .stock(4)
                    .sku("M4ADES")
                    .destacado(true)
                    .imagenUrl("/img/skins/M4A4-DESOLATE.png")
                    .categoria(rifles)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("AWP Asiimov")
                    .precio(120000)
                    .stock(3)
                    .sku("AWPASIM")
                    .destacado(true)
                    .imagenUrl("/img/skins/AWP-ASIIMOV.png")
                    .categoria(rifles)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("AWP Dragon Lore")
                    .precio(1000000)
                    .stock(2)
                    .sku("AWPDRA")
                    .destacado(true)
                    .imagenUrl("/img/skins/AWP-DRAGON.png")
                    .categoria(rifles)
                    .build());

            // =======================================================
            // PISTOLAS
            // =======================================================
            productoRepo.save(Producto.builder()
                    .nombre("Glock-18 Fade")
                    .precio(45000)
                    .stock(5)
                    .sku("GCKFAD1")
                    .destacado(true)
                    .imagenUrl("/img/skins/GLOCK-FADE.png")
                    .categoria(pistolas)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("Glock-18 Water Elemental")
                    .precio(20000)
                    .stock(12)
                    .sku("GCKWAT1")
                    .destacado(false)
                    .imagenUrl("/img/skins/GLOCK-WATERELEMENTAL.png")
                    .categoria(pistolas)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("USP-S Kill Confirmed")
                    .precio(80000)
                    .stock(4)
                    .sku("USPKC01")
                    .destacado(true)
                    .imagenUrl("/img/skins/USP-KILL-CONFIRMED.png")
                    .categoria(pistolas)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("Desert Eagle Blaze")
                    .precio(150000)
                    .stock(3)
                    .sku("DEAGBZ1")
                    .destacado(true)
                    .imagenUrl("/img/skins/DEAGLE-BLAZE.png")
                    .categoria(pistolas)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("Desert Eagle Code Red")
                    .precio(10000)
                    .stock(3)
                    .sku("DEAGCR")
                    .destacado(true)
                    .imagenUrl("/img/skins/DesertEagle-Red.png")
                    .categoria(pistolas)
                    .build());

            // =======================================================
            // CUCHILLOS
            // =======================================================
            productoRepo.save(Producto.builder()
                    .nombre("Karambit Fade")
                    .precio(250000)
                    .stock(2)
                    .sku("KARAFAD")
                    .destacado(true)
                    .imagenUrl("/img/skins/KARAMBIT-FADE.png")
                    .categoria(cuchillos)
                    .build());

            productoRepo.save(Producto.builder()
                    .nombre("M9 Bayonet Urban")
                    .precio(300000)
                    .stock(1)
                    .sku("BAYURB")
                    .destacado(false)
                    .imagenUrl("/img/skins/BAYONET-URBAN.png")
                    .categoria(cuchillos)
                    .build());

            // =======================================================
            // GUANTES
            // =======================================================
            productoRepo.save(Producto.builder()
                    .nombre("Gloves Specialist")
                    .precio(200000)
                    .stock(3)
                    .sku("GLVSPL")
                    .destacado(true)
                    .imagenUrl("/img/skins/Gloves-Specialist.png")
                    .categoria(guantes)
                    .build());

            System.out.println(">>> DataSeeder ejecutado correctamente.");
        };
    }

    // =======================================================
    // MÉTODO SHA-256
    // =======================================================
    private String sha256(String base) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
