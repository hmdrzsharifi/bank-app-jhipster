import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISavingsAccountTransaction, defaultValue } from 'app/shared/model/savings-account-transaction.model';

export const ACTION_TYPES = {
  FETCH_SAVINGSACCOUNTTRANSACTION_LIST: 'savingsAccountTransaction/FETCH_SAVINGSACCOUNTTRANSACTION_LIST',
  FETCH_SAVINGSACCOUNTTRANSACTION: 'savingsAccountTransaction/FETCH_SAVINGSACCOUNTTRANSACTION',
  CREATE_SAVINGSACCOUNTTRANSACTION: 'savingsAccountTransaction/CREATE_SAVINGSACCOUNTTRANSACTION',
  UPDATE_SAVINGSACCOUNTTRANSACTION: 'savingsAccountTransaction/UPDATE_SAVINGSACCOUNTTRANSACTION',
  DELETE_SAVINGSACCOUNTTRANSACTION: 'savingsAccountTransaction/DELETE_SAVINGSACCOUNTTRANSACTION',
  RESET: 'savingsAccountTransaction/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISavingsAccountTransaction>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SavingsAccountTransactionState = Readonly<typeof initialState>;

// Reducer

export default (state: SavingsAccountTransactionState = initialState, action): SavingsAccountTransactionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SAVINGSACCOUNTTRANSACTION):
    case REQUEST(ACTION_TYPES.UPDATE_SAVINGSACCOUNTTRANSACTION):
    case REQUEST(ACTION_TYPES.DELETE_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION):
    case FAILURE(ACTION_TYPES.CREATE_SAVINGSACCOUNTTRANSACTION):
    case FAILURE(ACTION_TYPES.UPDATE_SAVINGSACCOUNTTRANSACTION):
    case FAILURE(ACTION_TYPES.DELETE_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SAVINGSACCOUNTTRANSACTION):
    case SUCCESS(ACTION_TYPES.UPDATE_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SAVINGSACCOUNTTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/savings-account-transactions';

// Actions

export const getEntities: ICrudGetAllAction<ISavingsAccountTransaction> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION_LIST,
  payload: axios.get<ISavingsAccountTransaction>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISavingsAccountTransaction> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SAVINGSACCOUNTTRANSACTION,
    payload: axios.get<ISavingsAccountTransaction>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISavingsAccountTransaction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SAVINGSACCOUNTTRANSACTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISavingsAccountTransaction> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SAVINGSACCOUNTTRANSACTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISavingsAccountTransaction> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SAVINGSACCOUNTTRANSACTION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
