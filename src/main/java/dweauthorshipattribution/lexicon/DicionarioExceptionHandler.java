package dweauthorshipattribution.lexicon;

//import br.ufpb.compiladores.projetofinal.util.logger.ILogger;
//import br.ufpb.compiladores.projetofinal.util.logger.LoggerFactory;

public class DicionarioExceptionHandler {
//	private static final ILogger LOGGER = LoggerFactory.getLogger(DicionarioExceptionHandler.class);

	public void handle(Exception e) {
//		LOGGER.trace(e.getMessage(),e);
//		LOGGER.error(e.getMessage());
//		LOGGER.customDebug("Erro: %s", e.getMessage());

		throw new DicionarioException(e.getMessage(),e);
	}

}
