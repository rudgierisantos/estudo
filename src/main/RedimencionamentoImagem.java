package main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class RedimencionamentoImagem {

	private static final Logger LOGGER = Logger.getLogger(RedimencionamentoImagem.class.getName());

	public static void main(String[] args) {
		
		long startTime = System.nanoTime(); // Inicia a contagem de tempo

		String origem = "C:\\Users\\Rudgieri Santos\\Documents\\imagens\\origem";
		String destino = "C:\\Users\\Rudgieri Santos\\Documents\\imagens\\destino";

		int maxWidth = 1024;
		int maxHeight = 1024;
		float quality = 0.8f; // 80% da qualidade original

		processarImagens(origem, destino, maxWidth, maxHeight, quality);
		
		long endTime = System.nanoTime(); // Finaliza a contagem de tempo
		LOGGER.info("******** Redimensionamento concluído em " + ((endTime - startTime) / 1_000_000) + " ms. ********");
	}

	public static void processarImagens(String origem, String destino, int maxWidth, int maxHeight, float quality) {
		try {
			Files.createDirectories(Paths.get(destino)); // Garante que a pasta de destino exista

			try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(origem))) {
				for (Path filePath : stream) {
					if (Files.isRegularFile(filePath) && isImagem(filePath)) {
						LOGGER.info("******* Processando: " + filePath.getFileName() + "*******");

						byte[] imageBytes = Files.readAllBytes(filePath);
						byte[] resizedImage = resizeImage(imageBytes, maxWidth, maxHeight, quality);

						Path destinoPath = Paths.get(destino, filePath.getFileName().toString());
						Files.write(destinoPath, resizedImage);

						LOGGER.info("******* Imagem salva em: " + destinoPath + "*******");
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro ao processar imagens", e);
		}
	}

	public static boolean isImagem(Path filePath) {
		String nome = filePath.toString().toLowerCase();
		return nome.endsWith(".jpg") || nome.endsWith(".jpeg") || nome.endsWith(".png");
	}

	public static byte[] resizeImage(byte[] imageBytes, int maxWidth, int maxHeight, float quality) throws IOException {
		long startTime = System.nanoTime(); // Inicia a contagem de tempo

		LOGGER.info("******** Iniciando redimensionamento da imagem... ********");

		// Lê a imagem a partir dos bytes
		ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
		BufferedImage originalImage = ImageIO.read(bais);

		if (originalImage == null) {
			throw new IOException("Não foi possível ler a imagem.");
		}

		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		LOGGER.info("******** Dimensões da imagem original: " + width + "x" + height + " ********");

		// Se a imagem já for pequena, não redimensiona
		if (width <= maxWidth && height <= maxHeight) {
			LOGGER.info("******** Imagem já está dentro do tamanho desejado. Nenhuma alteração feita. ********");
			return imageBytes;
		}

		// Calcula novas dimensões mantendo a proporção
		double scale = Math.min((double) maxWidth / width, (double) maxHeight / height);
		int newWidth = (int) (width * scale);
		int newHeight = (int) (height * scale);

		LOGGER.info("******** Redimensionando para: " + newWidth + "x" + newHeight + " ********");

		// Redimensiona a imagem
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g2d.dispose();

		// Salva a imagem redimensionada em um array de bytes com compressão
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality); // Define a qualidade da imagem (0.0f a 1.0f)

		LOGGER.info("******** Aplicando compressão com qualidade: " + quality + " ********");

		writer.write(null, new javax.imageio.IIOImage(resizedImage, null, null), param);
		writer.dispose();
		ios.close();

		long endTime = System.nanoTime(); // Finaliza a contagem de tempo
		LOGGER.info("******** Redimensionamento concluído em " + ((endTime - startTime) / 1_000_000) + " ms. ********");

		return baos.toByteArray();
	}

}
