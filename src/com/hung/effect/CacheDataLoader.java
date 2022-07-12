/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hung.effect;

import com.hung.gameobjects.PhysicalMap;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/**
 *
 * @author manhh
 */
public class CacheDataLoader {
    
    private static CacheDataLoader instance;
    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";
    private String physmapfile = "data/map.txt";
    private String physmapfile2 = "data/map2.txt";
    
    private Hashtable<String, FrameImage> frameImages; 
    private Hashtable<String, Animation> animations;
    
    private int[][] phys_map;
    private int[][] phys_map2;
    
    private CacheDataLoader() {
    }
    
    public static CacheDataLoader getInstance(){
        if(instance==null)
            instance=new CacheDataLoader();
        return instance;
    }
    
    
    public FrameImage getFrameImage(String name){

        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;

    }
    
    public Animation getAnimation(String name){
        
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
        
    }
    
    public FrameImage getBackGroundMap(int maptype){
        if(maptype==PhysicalMap.MAP1){
            FrameImage frameImage = new FrameImage(instance.frameImages.get("map"));
            return frameImage;
        }else{
            FrameImage frameImage = new FrameImage(instance.frameImages.get("map2"));
            return frameImage;
        }

    }
    
    public int[][] getPhysicalMap(int maptype){
        if(maptype==1) return instance.phys_map;
        else return instance.phys_map2;
    }
    
    public void LoadData()throws IOException{
        
        LoadFrame();
        LoadAnimation();
        LoadPhysMap(physmapfile);
        LoadPhysMap2(physmapfile2);
//        LoadBackgroundMap();
//        LoadSounds();
        
    }
    
    public void LoadFrame() throws IOException{
        
        frameImages = new Hashtable<String, FrameImage>();
        
        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(framefile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            
            int n = Integer.parseInt(line);
            String path = null;
            BufferedImage imageData = null;
            int i2 = 0;
            for(int i = 0;i < n; i ++){
                
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                
                boolean refreshImage = (path == null || !path.equals(str[1]));
                path = str[1];
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);
                
                if(refreshImage) {
                    refreshImage = false;
                    imageData = ImageIO.read(new File(path));
                }
                if(imageData != null) {
                    BufferedImage image = imageData.getSubimage(x, y, w, h);
                    image=FrameImage.resizeImage(image, w*2, h*2);
                    frame.setImage(image);
                    
                }
                
                instance.frameImages.put(frame.getName(), frame);
            }
            
        }
        
        br.close();
        
        BufferedImage imageData2 = null;
        imageData2 = ImageIO.read(new File("data/project.png"));
        imageData2 = FrameImage.resizeImage(imageData2,imageData2.getWidth()*2,imageData2.getHeight()*2);
        FrameImage mainframeImage = new FrameImage("map",imageData2);
        instance.frameImages.put(mainframeImage.getName(), mainframeImage);
        
        imageData2 = ImageIO.read(new File("data/project2.png"));
        imageData2 = FrameImage.resizeImage(imageData2,imageData2.getWidth()*2,imageData2.getHeight()*2);
        mainframeImage = new FrameImage("map2",imageData2);
        instance.frameImages.put(mainframeImage.getName(), mainframeImage);
        
    }
    
    
    public void LoadAnimation() throws IOException {
        
        animations = new Hashtable<String, Animation>();
        
        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                Animation animation = new Animation();
                
                while((line = br.readLine()).equals(""));
                animation.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                
                for(int j = 0;j<str.length;j+=2){
                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j+1]));
                    
                }
                
                instance.animations.put(animation.getName(), animation);
                
            }
            
        }
        
        br.close();
    }
    
    public void LoadPhysMap(String phy_file) throws IOException{
        
        FileReader fr = new FileReader(phy_file);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        instance.phys_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            line = line.trim();
	    line = line.replaceAll("\\s+", " ");
            String [] str = line.split(" ");
            for(int j = 0;j<numberOfColumns;j++)
                instance.phys_map[i][j] = Integer.parseInt(str[j]);
        }
        
//        for(int i = 0;i < numberOfRows;i++){
//            
//            for(int j = 0;j<numberOfColumns;j++)
//                System.out.print(" "+instance.phys_map[i][j]);
//            
//            System.out.println();
//        }
        
        br.close();
        
    }
    
    public void LoadPhysMap2(String phy_file) throws IOException{
        
        FileReader fr = new FileReader(phy_file);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        instance.phys_map2 = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            line = line.trim();
	    line = line.replaceAll("\\s+", " ");
            String [] str = line.split(" ");
            for(int j = 0;j<numberOfColumns;j++)
                instance.phys_map2[i][j] = Integer.parseInt(str[j]);
        }
        
//        for(int i = 0;i < numberOfRows;i++){
//            
//            for(int j = 0;j<numberOfColumns;j++)
//                System.out.print(" "+instance.phys_map[i][j]);
//            
//            System.out.println();
//        }
        
        br.close();
        
    }
}
