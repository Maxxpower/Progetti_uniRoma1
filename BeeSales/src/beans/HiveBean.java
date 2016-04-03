package beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.jpa.Hive;
import beans.jpa.HiveManager;

@ManagedBean

public class HiveBean implements Serializable{

	private Part xmlFile;
	private List<Hive> storicoArnie;

	@PostConstruct
	public void init() {
		System.out.println("PostConstruct richiamato!!");
		HiveManager hm = new HiveManager();
		storicoArnie = hm.getAll();

	}

	public List<Hive> getStoricoArnie() {
		return storicoArnie;
	}

	public void setStoricoArnie(List<Hive> storicoArnie) {
		this.storicoArnie = storicoArnie;
	}

	public Part getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(Part xmlFile) {
		this.xmlFile = xmlFile;
	}

	// ActionControllers
	// action controller for xml upload
	public String uploadRecord() {

		try {
			InputStream in = xmlFile.getInputStream();
			// Da ricontrollare
			// Files.copy(in, new File("").toPath());

			// ora opero sull'xml e inserisco il record sul DB

			parseInsertRecord(in);
			HiveManager hm = new HiveManager();
			storicoArnie = hm.getAll();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String setEditableRecord(Hive h) {

		h.setEditable(true);
		return null;

	}

	public String editRecordInPlace(Hive h) {

		System.out.println("Test " + h);
		return null;

	}

	private void parseInsertRecord(InputStream fileRecord)
			throws ParserConfigurationException, SAXException, IOException, ParseException {

		Hive hive = new Hive();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fileRecord);
		doc.getDocumentElement().normalize();
		// LOGGER.info("Root element :" +
		// doc.getDocumentElement().getNodeName());
		NodeList recordNList = doc.getElementsByTagName("RECORD");
		for (int temp = 0; temp < recordNList.getLength(); temp++) {
			Node recordNode = recordNList.item(temp);
			// LOGGER.info("\nCurrent Element :" + recordNode.getNodeName());
			if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) recordNode;
				String name = eElement.getAttribute("hive");
				String checked_at = eElement.getAttribute("date");

				// LOGGER.info("hive id : " + name + " check date:" +
				// checked_at);
				hive.setName(name);
				hive.setChecked_at(new Timestamp(sdf.parse(checked_at).getTime()));

			}
		}
		NodeList queenBeeNList = doc.getElementsByTagName("QUEEN_BEE_CHECK");
		// SimpleDateFormat queenSDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < queenBeeNList.getLength(); temp++) {
			Node queenNode = queenBeeNList.item(temp);
			// LOGGER.info("\nCurrent Element :" + queenNode.getNodeName());
			if (queenNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) queenNode;
				String check_date = eElement.getAttribute("check_date");
				if (check_date != null && !check_date.equals("null")) {
					hive.setQueen_bee_checked_at(new Timestamp(sdf.parse(check_date).getTime()));
					// LOGGER.info("queen bee check date:" + check_date);
				}
				String color = eElement.getAttribute("color");
				if (color != null && !color.equals("null")) {
					hive.setQueen_bee_color(color);
					// LOGGER.info("queen bee color:" + color);
				}
				String goodness = eElement.getAttribute("goodness");
				if (goodness != null && !goodness.equals("null")) {
					hive.setQueen_bee_goodness(new Integer(goodness));
					// LOGGER.info("queen bee goodness:" + goodness);
				}
				String brood_state = eElement.getAttribute("brood_state");
				if (brood_state != null && !brood_state.equals("null")) {
					hive.setBrood_state(brood_state);
					// LOGGER.info("queen bee brood_state:" + brood_state);
				}
				String fleeting_state = eElement.getAttribute("fleeting_state");
				if (fleeting_state != null && !fleeting_state.equals("null")) {
					hive.setFleeting_state(fleeting_state);
					// LOGGER.info("queen bee fleeting_state:" +
					// fleeting_state);
				}

			}
		}

		NodeList winterCheckNList = doc.getElementsByTagName("WINTER_CHECK");
		// SimpleDateFormat winterSDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < winterCheckNList.getLength(); temp++) {
			Node winterParentNode = winterCheckNList.item(temp);
			// LOGGER.info("\nCurrent Element :" +
			// winterParentNode.getNodeName());
			if (winterParentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) winterParentNode;
				String honey_supplying = eElement.getAttribute("honey_supplying");
				if (honey_supplying != null && !honey_supplying.equals("null")) {
					hive.setHoney_supplying(honey_supplying);
					// LOGGER.info("winter honey_supplying:" + honey_supplying);
				}
				String royal_cells_status = eElement.getAttribute("royal_cells_status");
				if (royal_cells_status != null && !royal_cells_status.equals("null")) {
					hive.setRoyal_cells_status(royal_cells_status);
					// LOGGER.info("winter royal_cells_status:" +
					// royal_cells_status);
				}
				String block_blood_start_date = eElement.getAttribute("block_blood_start_date");
				if (block_blood_start_date != null && !block_blood_start_date.equals("null")) {
					hive.setBlock_blood_start_date(new Timestamp(sdf.parse(block_blood_start_date).getTime()));
					// LOGGER.info("winter block_blood_start_date:" +
					// block_blood_start_date);
				}
				String block_blood_end_date = eElement.getAttribute("block_blood_end_date");
				if (block_blood_end_date != null && !block_blood_end_date.equals("null")) {
					hive.setBlock_blood_end_date(new Timestamp(sdf.parse(block_blood_end_date).getTime()));
					// LOGGER.info("winter block_blood_end_date:" +
					// block_blood_end_date);
				}
			}
		}

		NodeList varroaCheckNList = doc.getElementsByTagName("VARROA_CHECK");
		// SimpleDateFormat varroaSDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < varroaCheckNList.getLength(); temp++) {
			Node varroaParentNode = varroaCheckNList.item(temp);
			// LOGGER.info("\nCurrent Element :" +
			// varroaParentNode.getNodeName());
			if (varroaParentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) varroaParentNode;
				String check_date = eElement.getAttribute("check_date");
				if (check_date != null && !check_date.equals("null")) {
					hive.setVarroa_checked_at(new Timestamp(sdf.parse(check_date).getTime()));
					// LOGGER.info("varroa check_date:" + check_date);
				}
				String status = eElement.getAttribute("status");
				if (status != null && !status.equals("null")) {
					hive.setVarroa_status(status);
					// LOGGER.info("varroa status:" + status);
				}
				String trayCheckDate = eElement.getAttribute("tray_check_date");
				if (trayCheckDate != null && !trayCheckDate.equals("null")) {
					hive.setTray_checked_at(new Timestamp(sdf.parse(trayCheckDate).getTime()));
					// LOGGER.info("tray Check Date:" + trayCheckDate);
				}
			}
		}

		NodeList syrup1CheckNList = doc.getElementsByTagName("SYRUP_1_CHECK");
		// SimpleDateFormat syrupSDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < syrup1CheckNList.getLength(); temp++) {
			Node syrup1ParentNode = syrup1CheckNList.item(temp);
			// LOGGER.info("\nCurrent Element :" +
			// syrup1ParentNode.getNodeName());
			if (syrup1ParentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) syrup1ParentNode;
				String check_date = eElement.getAttribute("check_date");
				if (check_date != null && !check_date.equals("null")) {
					hive.setSyrup1_supplied_at(new Timestamp(sdf.parse(check_date).getTime()));
					// LOGGER.info("syrup1 supplied at :" + check_date);
				}

			}
		}

		NodeList syrup2CheckNList = doc.getElementsByTagName("SYRUP_2_CHECK");
		// SimpleDateFormat syrup2SDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < syrup2CheckNList.getLength(); temp++) {
			Node syrup2ParentNode = syrup2CheckNList.item(temp);
			// LOGGER.info("\nCurrent Element :" +
			// syrup2ParentNode.getNodeName());
			if (syrup2ParentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) syrup2ParentNode;
				String check_date = eElement.getAttribute("check_date");
				if (check_date != null && !check_date.equals("null")) {
					hive.setSyrup2_supplied_at(new Timestamp(sdf.parse(check_date).getTime()));
					// LOGGER.info("syrup2 supplied at :" + check_date);
				}

			}
		}

		NodeList candyCheckNList = doc.getElementsByTagName("CANDY_CHECK");
		// SimpleDateFormat candySDF = new SimpleDateFormat("dd/MMM/yyyy",
		// Locale.ENGLISH);
		for (int temp = 0; temp < candyCheckNList.getLength(); temp++) {
			Node candyParentNode = candyCheckNList.item(temp);
			// LOGGER.info("\nCurrent Element :" +
			// candyParentNode.getNodeName());
			if (candyParentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) candyParentNode;
				String check_date = eElement.getAttribute("check_date");
				if (check_date != null && !check_date.equals("null")) {
					hive.setCandy_supplied_at(new Timestamp(sdf.parse(check_date).getTime()));
					// LOGGER.info("candy supplied at :" + check_date);
				}

				String qty = eElement.getAttribute("qty");
				if (qty != null && !qty.equals("null")) {

					switch (qty) {
					case "1/2":
						hive.setCandy_qty(0.5d);
						break;
					case "1":
						hive.setCandy_qty(1d);
						break;
					case "1/4":
						hive.setCandy_qty(0.25d);
						break;
					default:
						break;
					}

					// LOGGER.info("candy ty :" + qty);
				}

			}
		}

		NodeList honeyNList = doc.getElementsByTagName("HONEY_PRODUCTION");
		for (int temp = 0; temp < honeyNList.getLength(); temp++) {
			Node honeyNode = honeyNList.item(temp);
			// LOGGER.info("\nCurrent Element :" + honeyNode.getNodeName());
			if (honeyNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) honeyNode;
				String type = eElement.getAttribute("type");
				if (type != null && !type.equals("null")) {
					hive.setHoney_production_type(type);
					// LOGGER.info("honey type :" + type);
				}

				String qty_kg = eElement.getAttribute("qty_kg");
				if (qty_kg != null && !qty_kg.equals("null")) {
					hive.setHoney_production_qty(new Double(qty_kg));
					// LOGGER.info("honey qty :" + qty_kg);
				}

				String qty_frame_number = eElement.getAttribute("qty_frame_number");
				if (qty_frame_number != null && !qty_frame_number.equals("null")) {
					hive.setQty_frame_number(new Integer(qty_frame_number));
					// LOGGER.info("qty_frame_number :" + qty_frame_number);
				}

			}
		}

		HiveManager manager = new HiveManager();
		manager.insert(hive);

	}

}
