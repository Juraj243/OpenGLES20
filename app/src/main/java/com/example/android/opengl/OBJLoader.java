package com.example.android.opengl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Juraj on 20.10.2018.
 */

public class OBJLoader {
    private List<String> verticesList = new ArrayList<>();
    private List<String> facesList = new ArrayList<>();
    private List<String> texturesList = new ArrayList<>();
    private List<String> normalsList = new ArrayList<>();
    private List<Integer> verticesOrder = new ArrayList<>();
    private float[] vertices = null;
    private int[] indices = null;
    private Context context;

    public OBJLoader(Context context) {
        this.context = context;
    }

    public void loadModel(String file) {
        try {
            Scanner scanner = new Scanner(context.getAssets().open(file + ".obj"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("v ")) {
                    verticesList.add(line);
                } else if (line.startsWith("vt ")) {
                    texturesList.add(line);
                } else if (line.startsWith("vn ")) {
                    normalsList.add(line);
                } else if (line.startsWith("f ")) {
                    System.out.println(line);
                    facesList.add(line);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (facesList.get(0).contains("/")) {
            splitFacesWithTextures();
        } else {
            splitFaces();
        }

        vertices = new float[verticesList.size() * 3];
        indices = new int[verticesOrder.size()];

        int vPointer = 0;
        for (String vertex : verticesList) {
            String[] curVertex = vertex.split(" +");
            System.out.println(curVertex);
            vertices[vPointer++] = Float.parseFloat(curVertex[1]);
            vertices[vPointer++] = Float.parseFloat(curVertex[2]);
            vertices[vPointer++] = Float.parseFloat(curVertex[3]);
        }

        for (int i = 0; i < verticesOrder.size(); i++) {
            indices[i] = verticesOrder.get(i);
            System.out.println(indices[i]);
        }
    }

    private void splitFacesWithTextures() {
        for (String line : facesList) {
            String[] curLine = line.split(" ");
            String[] vertex1 = curLine[1].split("/");
            String[] vertex2 = curLine[2].split("/");
            String[] vertex3 = curLine[3].split("/");
            processVertex(vertex1[0], verticesOrder);
            processVertex(vertex2[0], verticesOrder);
            processVertex(vertex3[0], verticesOrder);
        }
    }

    private void splitFaces() {
        for (String line : facesList) {
            String[] curLine = line.split(" ");
            String vertex1 = curLine[2];
            String vertex2 = curLine[4];
            String vertex3 = curLine[6];
            processVertex(vertex1, verticesOrder);
            processVertex(vertex2, verticesOrder);
            processVertex(vertex3, verticesOrder);
        }
    }

    private void processVertex(String vertexData, List<Integer> indices) {
        int vertexPosition = Integer.parseInt(vertexData) - 1;
        indices.add(vertexPosition);
        System.out.println("vertex index: " + vertexPosition);
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }
}
