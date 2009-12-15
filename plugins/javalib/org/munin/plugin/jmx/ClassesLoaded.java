package org.munin.plugin.jmx;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
public class ClassesLoaded {

    public static void main(String args[])throws FileNotFoundException,IOException {
        String[] connectionInfo = ConfReader.GetConnectionInfo();

        if (args.length == 1) {
            if (args[0].equals("config")) {
                System.out.println(
                     "graph_title JVM (port " + connectionInfo[1] + ") ClassesLoaded\n" +
                     "graph_vlabel classes\n" +
		     "graph_category " + connectionInfo[2] + "\n" +
                     "graph_info The number of classes that are currently loaded in the Java virtual machine.\n" +
                     "ClassesLoaded.label ClassesLoaded\n"
);
            }
         else {

         try{
            MBeanServerConnection connection = BasicMBeanConnection.get();
            ClassLoadingMXBean classmxbean=ManagementFactory.newPlatformMXBeanProxy(connection, ManagementFactory.CLASS_LOADING_MXBEAN_NAME, ClassLoadingMXBean.class);

            System.out.println("ClassesLoaded.value "+classmxbean.getLoadedClassCount());
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    }
}

}
