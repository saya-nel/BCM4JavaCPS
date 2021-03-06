package port;

import components.Broker;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ManagementCI;
import interfaces.MessageFilterI;

/**
 * Port d'entrée du Broker pour l'interface composant ManagementCI
 * 
 * @author Bello Velly
 *
 */
public class BrokerManagementInboundPort extends AbstractInboundPort implements ManagementCI {

	private static final long serialVersionUID = 1L;

	/**
	 * index du pool de thread sur lequel on va éxécuté les appels
	 */
	protected final int executorIndex;

	/**
	 * Constructeur de BrokerManagementInboundPort
	 * 
	 * @param uri           uri du port
	 * @param executorIndex @see {@link #executorIndex}
	 * @param owner         composant qui possède le port
	 */
	public BrokerManagementInboundPort(String uri, int executorIndex, ComponentI owner) throws Exception {
		super(uri, ManagementCI.class, owner);
		assert owner instanceof Broker;
		this.executorIndex = executorIndex;
	}

	// TODO : revoir les appels synchrones / asynchrones (voir fiche prof sur
	// soutenance mi semestre)

	/**
	 * @see interfaces.ManagementImplementationI#createTopic(String)
	 */
	@Override
	public void createTopic(String topic) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).createTopic(topic));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.ManagementImplementationI#createTopics(String[])
	 */
	@Override
	public void createTopics(String[] topics) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).createTopics(topics));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.ManagementImplementationI#destroyTopic(String)
	 */
	@Override
	public void destroyTopic(String topic) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).destroyTopic(topic));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.ManagementImplementationI#isTopic(String)
	 */
	@Override
	public boolean isTopic(String topic) {
		try {
			return this.getOwner().handleRequestSync(executorIndex, owner -> ((Broker) owner).isTopic(topic));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @see interfaces.ManagementImplementationI#getTopics()
	 */
	@Override
	public String[] getTopics() {
		try {
			return this.getOwner().handleRequestSync(executorIndex, owner -> ((Broker) owner).getTopics());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @see interfaces.ManagementImplementationI#getPublicationPortURI()
	 */
	@Override
	public String getPublicationPortURI() throws Exception {
		return this.getOwner().handleRequestSync(executorIndex, owner -> ((Broker) owner).getPublicationPortURI());
	}

	/**
	 * @see interfaces.SubscriptionImplementationI#subscribe(String, String)
	 */
	@Override
	public void subscribe(String topic, String inboundPortURI) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).subscribe(topic, inboundPortURI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.SubscriptionImplementationI#subscribe(String[], String)
	 */
	@Override
	public void subscribe(String[] topics, String inboundPortURI) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).subscribe(topics, inboundPortURI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.SubscriptionImplementationI#subscribe(String, MessageFilterI,
	 *      String)
	 */
	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortURI) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).subscribe(topic, filter, inboundPortURI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.SubscriptionImplementationI#modifyFilter(String,
	 *      MessageFilterI, String)
	 */
	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortURI) {
		try {
			this.getOwner().runTask(executorIndex,
					owner -> ((Broker) owner).modifyFilter(topic, newFilter, inboundPortURI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see interfaces.SubscriptionImplementationI#unsubscribe(String, String)
	 */
	@Override
	public void unsubscribe(String topic, String inboundPortURI) {
		try {
			this.getOwner().runTask(executorIndex, owner -> ((Broker) owner).unsubscribe(topic, inboundPortURI));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
