/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package iia.dsl;

import iia.dsl.Tareas.Aggregator;
import iia.dsl.Tareas.ContextEnricher;
import iia.dsl.Tareas.Correlator2;
import iia.dsl.Tareas.Distributor;
import iia.dsl.Tareas.Merger;
import iia.dsl.Tareas.Replicator;
import iia.dsl.Tareas.Splitter;
import iia.dsl.Tareas.Tarea_Ejemplo;
import iia.dsl.Tareas.Translator;
import iia.dsl.port.Entry_port;
import iia.dsl.port.Exit_port;
import iia.dsl.port.Sol_port;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Manuel
 *
 */
public class Cliente {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        //Ejecuta la solución de Cafe
        Cafe("./order6.xml", "SalidaFinal.xml");

        //Ejecuta la solución de Cafe realizando un seguimiento de los datos
        //para facilitarr su corrección
        //Cafe_Seguimiento("./order6.xml", "SalidaFinal.xml");
    }

    public static void Cafe(String fEntrada, String fSalida) {
        
            System.out.println("Iniciando programa");
            //Slots
            Slot siSplitter = new Slot();
            Slot soSplitter = new Slot();
            Slot soDistributorF = new Slot();
            Slot soDistributorC = new Slot();
            Slot siCorrelatorF = new Slot();
            Slot siCorrelatorC = new Slot();
            Slot siTranslatorF = new Slot();
            Slot siTranslatorC = new Slot();
            Slot siBarmanF = new Slot();
            Slot siBarmanC = new Slot();
            Slot soBarmanF = new Slot();
            Slot soBarmanC = new Slot();
            Slot siContextF = new Slot();
            Slot siContextC = new Slot();
            Slot siBodyF = new Slot();
            Slot siBodyC = new Slot();
            Slot siMergeF = new Slot();
            Slot siMergeC = new Slot();
            Slot siAggregator = new Slot();
            Slot soAggregator = new Slot();

            //Puertos
            Exit_port pComandas = new Exit_port(siSplitter);
            Sol_port pBarmanF = new Sol_port(siBarmanF, soBarmanF);
            Sol_port pBarmanC = new Sol_port(siBarmanC, soBarmanC);
            Entry_port pCamarero = new Entry_port(soAggregator);

            //Conectores
            Comandas comanda = new Comandas(pComandas, fEntrada);
            Barman barmanF = new Barman(pBarmanF);
            Barman barmanC = new Barman(pBarmanC);
            Camarero camarero = new Camarero(pCamarero, fSalida);

            //Material para tareas
            ArrayList<Slot> inputSlotListF = new ArrayList<Slot>();
            inputSlotListF.add(soBarmanF);
            inputSlotListF.add(siCorrelatorF);

            ArrayList<Slot> outputSlotListF = new ArrayList<Slot>();
            outputSlotListF.add(siContextF);
            outputSlotListF.add(siBodyF);

            ArrayList<Slot> inputSlotListC = new ArrayList<Slot>();
            inputSlotListC.add(soBarmanC);
            inputSlotListC.add(siCorrelatorC);

            ArrayList<Slot> outputSlotListC = new ArrayList<Slot>();
            outputSlotListC.add(siContextC);
            outputSlotListC.add(siBodyC);

            //Tareas
            Splitter spliter = new Splitter(siSplitter, soSplitter, "//drinks/drink");
            Distributor distributor = new Distributor(soSplitter, "//drink[type='hot']", soDistributorC, "//drink[type='cold']", soDistributorF);
            Replicator replicatorF = new Replicator(soDistributorF, siCorrelatorF, siTranslatorF);
            Replicator replicatorC = new Replicator(soDistributorC, siCorrelatorC, siTranslatorC);
            Translator translatorF = new Translator(siTranslatorF, siBarmanF);
            Translator translatorC = new Translator(siTranslatorC, siBarmanC);
            Correlator2 correlatorF = new Correlator2(inputSlotListF, outputSlotListF, "//id_correlacion");
            Correlator2 correlatorC = new Correlator2(inputSlotListC, outputSlotListC, "//id_correlacion");
            ContextEnricher contextEnricherF = new ContextEnricher(siContextF, siBodyF, siMergeF);
            ContextEnricher contextEnricherC = new ContextEnricher(siContextC, siBodyC, siMergeC);
            Merger merger = new Merger(siAggregator, siMergeF, siMergeC);
            Aggregator aggregator = new Aggregator(siAggregator, soAggregator);

            //Run
            comanda.readXML();
            spliter.splitter();
            distributor.distributor();
            replicatorF.replicator();
            replicatorC.replicator();
            translatorC.TranslateSQL("*", "Availability", "name", "", "//name");
            translatorF.TranslateSQL("*", "Availability", "name", "", "//name");
            barmanC.serveDrinks();
            barmanF.serveDrinks();
            correlatorC.Correlator();
            correlatorF.Correlator();
            contextEnricherC.contextEnricher();
            contextEnricherF.contextEnricher();
            merger.merger();
            aggregator.aggregator();
            camarero.writeXML();

            System.out.println("############################ FINALIZADO ##################################");

       
        

    }

    public static void Cafe_Seguimiento(String fEntrada, String fSalida) {
       
            System.out.println("Iniciando programa");
            //Slots
            Slot siSplitter = new Slot();
            Slot soSplitter = new Slot();
            Slot soDistributorF = new Slot();
            Slot soDistributorC = new Slot();
            Slot siCorrelatorF = new Slot();
            Slot siCorrelatorC = new Slot();
            Slot siTranslatorF = new Slot();
            Slot siTranslatorC = new Slot();
            Slot siBarmanF = new Slot();
            Slot siBarmanC = new Slot();
            Slot soBarmanF = new Slot();
            Slot soBarmanC = new Slot();
            Slot siContextF = new Slot();
            Slot siContextC = new Slot();
            Slot siBodyF = new Slot();
            Slot siBodyC = new Slot();
            Slot siMergeF = new Slot();
            Slot siMergeC = new Slot();
            Slot siAggregator = new Slot();
            Slot soAggregator = new Slot();

            //Puertos
            Exit_port pComandas = new Exit_port(siSplitter);
            Sol_port pBarmanF = new Sol_port(siBarmanF, soBarmanF);
            Sol_port pBarmanC = new Sol_port(siBarmanC, soBarmanC);
            Entry_port pCamarero = new Entry_port(soAggregator);

            //Conectores
            Comandas comanda = new Comandas(pComandas, fEntrada);
            Barman barmanF = new Barman(pBarmanF);
            Barman barmanC = new Barman(pBarmanC);
            Camarero camarero = new Camarero(pCamarero, fSalida);

            //Material para tareas
            ArrayList<Slot> inputSlotListF = new ArrayList<Slot>();
            inputSlotListF.add(soBarmanF);
            inputSlotListF.add(siCorrelatorF);

            ArrayList<Slot> outputSlotListF = new ArrayList<Slot>();
            outputSlotListF.add(siContextF);
            outputSlotListF.add(siBodyF);

            ArrayList<Slot> inputSlotListC = new ArrayList<Slot>();
            inputSlotListC.add(soBarmanC);
            inputSlotListC.add(siCorrelatorC);

            ArrayList<Slot> outputSlotListC = new ArrayList<Slot>();
            outputSlotListC.add(siContextC);
            outputSlotListC.add(siBodyC);

            //Tareas
            Tarea_Ejemplo test = new Tarea_Ejemplo(soDistributorF);

            Splitter spliter = new Splitter(siSplitter, soSplitter, "//drinks/drink");
            Distributor distributor = new Distributor(soSplitter, "//drink[type='hot']", soDistributorC, "//drink[type='cold']", soDistributorF);
            Replicator replicatorF = new Replicator(soDistributorF, siCorrelatorF, siTranslatorF);
            Replicator replicatorC = new Replicator(soDistributorC, siCorrelatorC, siTranslatorC);
            Translator translatorF = new Translator(siTranslatorF, siBarmanF);
            Translator translatorC = new Translator(siTranslatorC, siBarmanC);
            Correlator2 correlatorF = new Correlator2(inputSlotListF, outputSlotListF, "//id_correlacion");
            Correlator2 correlatorC = new Correlator2(inputSlotListC, outputSlotListC, "//id_correlacion");
            ContextEnricher contextEnricherF = new ContextEnricher(siContextF, siBodyF, siMergeF);
            ContextEnricher contextEnricherC = new ContextEnricher(siContextC, siBodyC, siMergeC);
            Merger merger = new Merger(siAggregator, siMergeF, siMergeC);
            Aggregator aggregator = new Aggregator(siAggregator, soAggregator);

            //Run
            comanda.readXML();
            spliter.splitter();
            distributor.distributor();

            System.out.println("splitter");
            test.muestraTodo();

            System.out.println("soDistributorF**********");
            test.setInput(soDistributorF);
            test.muestraTodo();

            System.out.println("soDistributorC**********");
            test.setInput(soDistributorC);
            test.muestraTodo();

            replicatorF.replicator();

            System.out.println("siCorrelatorF**********");
            test.setInput(siCorrelatorF);
            test.muestraTodo();

            replicatorC.replicator();

            System.out.println("siTranslatorC**********");
            test.setInput(siTranslatorC);
            test.muestraTodo();

            translatorC.TranslateSQL("*", "Availability", "name", "", "//name");

            System.out.println("siBarmanC***********");
            test.setInput(siBarmanC);
            test.muestraTodo();

            translatorF.TranslateSQL("*", "Availability", "name", "", "//name");

            System.out.println("siBarmanF***********");
            test.setInput(siBarmanF);
            test.muestraTodo();

            barmanC.serveDrinks();
            barmanF.serveDrinks();

            System.out.println("soBarmanF***********");
            test.setInput(soBarmanF);
            test.muestraTodo();

            System.out.println("soBarmanC***********");
            test.setInput(soBarmanC);
            test.visualizeXML();

            correlatorC.Correlator();
            correlatorF.Correlator();

            System.out.println("siContextF***********");
            test.setInput(siContextF);
            test.muestraTodo();
            test.visualizeXML();

            System.out.println("siBodyF***********");
            test.setInput(siBodyF);
            test.muestraTodo();
            test.visualizeXML();

            contextEnricherC.contextEnricher();
            contextEnricherF.contextEnricher();
            System.out.println("siMergeF***********");
            test.setInput(siMergeF);
            test.muestraTodo();

            merger.merger();

            System.out.println("siAggregator*********** " + siAggregator.getListaMensajes().size());
            test.setInput(siAggregator);
            test.visualizeXML();

            aggregator.aggregator();

            System.out.println("soAggregator***********");
            test.setInput(soAggregator);
            test.muestraTodo();

            camarero.writeXML();

            System.out.println("############################ FINALIZADO ##################################");


    }
}
