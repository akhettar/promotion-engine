package com.akhettar;

import com.akhettar.model.ActivePromotions;
import com.akhettar.model.Combo;
import com.akhettar.model.Item;

import com.akhettar.pormotions.CombinedItemsForFixedPrice;
import com.akhettar.pormotions.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Promotion Engine
 */
public class PromotionEngine {

    private static final Logger logger = LoggerFactory.getLogger(PromotionEngine.class);

    /**
     * Calculate total by applying the list of promotions
     *
     * @param promotions
     * @return
     */
    public BigDecimal calculateTotal(final ShoppingCart cart, final ActivePromotions promotions) {
        CopyOnWriteArrayList<Item> toBeProcessed = new CopyOnWriteArrayList<>(cart.getItems());
        BigDecimal total = new BigDecimal(0);
        for (Item item : cart.getItems()) {

            // All items have been applied promotion to if any
            if (toBeProcessed.size() == 0) {
                logger.info("promotions processing completed");
                break;
            }

            // Check if there is promotion for the given item
            Promotion promotion = promotions.getPromotions().get(item.getSku().getID());

            // no promotions for this item
            if (promotion == null) {
               total = total.add(item.getSku().getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
               logger.info("no promotion for sku {}", item.getSku().getID());
               continue;
            }

            // promotion found
            if (promotion instanceof CombinedItemsForFixedPrice) {
                Optional<Item> itemB = toBeProcessed.stream().filter(i -> ((CombinedItemsForFixedPrice) promotion).getCombo().equals(
                        new Combo(item.getSku(), i.getSku()))).findFirst();

                // found the corresponding item to be combined
                if (itemB.isPresent()) {
                    logger.info("applying promotion {} to skus {},{}", promotion, item.getSku().getID(), itemB.get().getSku().getID());
                    total = total.add(promotion.apply(item.getQuantity(), itemB.get().getQuantity()));
                    toBeProcessed.remove(item);
                    toBeProcessed.remove(itemB.get());
                } else { // combo not found, promotion ignored
                    total = total.add(item.getSku().getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                    toBeProcessed.remove(item);
                }
            } else {
                logger.info("applying promotion {}", promotion);
                total = total.add(promotion.apply(item.getQuantity()));
                toBeProcessed.remove(item);
            }
        }
        return total;
    }
}
