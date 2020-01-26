package simple.engine;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PrefabSave {


    public static TilePrefab[] load(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException(file.getAbsolutePath().concat(" doesn't exist"));
        ArrayList<TilePrefab> prefabs = new ArrayList<>();
        String[] lines = loadLines(file).toArray(new String[]{});
        for (int i = 0; i < lines.length; i += 4) {
            prefabs.add(new TilePrefab(lines[i], lines[i + 1], Float.parseFloat(lines[i + 2]), Float.parseFloat(lines[i + 3])));
        }
        return prefabs.toArray(new TilePrefab[]{});
    }

    public static void save(File file, TilePrefab[] prefabs) throws IOException {
        if (!file.exists()) file.createNewFile();
        new PrintWriter(file).close();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Arrays.stream(prefabs).forEach(prefab -> {
            float breakTime = prefab.breakTime;
            float breakLevel = prefab.breakLevel;
            String name = prefab.name;
            String image = prefab.image;
            try {
                bufferedWriter.write(image);
                bufferedWriter.newLine();
                bufferedWriter.write(name);
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(breakLevel));
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(breakTime));
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }

    private static ArrayList<String> loadLines(File file) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) lines.add(scanner.nextLine());
        return lines;
    }

}
