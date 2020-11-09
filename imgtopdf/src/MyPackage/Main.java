package MyPackage;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;



public class Main {

	public static void main(String[] args) {
		try {
			String imgFile="img.jpg";
			String pdfFile="pdf.pdf";
			PDDocument document = new PDDocument();
			InputStream in = new FileInputStream(imgFile);
			BufferedImage bimg = ImageIO.read(in);
			float width = bimg.getWidth();
			float height = bimg.getHeight();
			PDPage page = new PDPage(new PDRectangle(width, height));
			document.addPage(page); 
			PDXObjectImage img = new PDJpeg(document, new FileInputStream(imgFile));
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.drawImage(img, 0, 0);
			contentStream.close();
			in.close();
			document.save(pdfFile);
			document.close();
			System.out.println("ok");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
