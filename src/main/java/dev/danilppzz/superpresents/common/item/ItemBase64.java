package dev.danilppzz.superpresents.common.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemBase64 {

    public static String encodeItemStack(ItemStack item) throws IOException
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

        bukkitObjectOutputStream.writeObject(item);
        bukkitObjectOutputStream.close();

        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public static ItemStack decodeItemStack(String base64) throws IOException, ClassNotFoundException
    {
        byte[] data = Base64.getDecoder().decode(base64);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

        ItemStack item = (ItemStack) bukkitObjectInputStream.readObject();
        bukkitObjectInputStream.close();

        return item;
    }
}
