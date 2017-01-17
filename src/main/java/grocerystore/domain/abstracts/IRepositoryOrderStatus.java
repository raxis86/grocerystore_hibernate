package grocerystore.domain.abstracts;

import grocerystore.domain.models.OrderStatus;

import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 */
public interface IRepositoryOrderStatus extends IRepository<OrderStatus,UUID> {
}
